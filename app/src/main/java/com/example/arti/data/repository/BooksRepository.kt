package com.example.arti.data.repository

import android.util.Log
import com.example.arti.data.database.AppDatabase
import com.example.arti.data.model.OpenLibraryBook
import com.example.arti.data.network.ApiResult
import com.example.arti.data.network.BooksRemoteDataSource
import com.example.arti.utils.asDatabaseModel
import com.example.arti.utils.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

const val TAG = "BooksRepository"

@Singleton
class BooksRepository @Inject constructor(
    // TODO: Need to create BooksLocalDataSource instead AppDatabase
    //  and gather everything in one Repository including BooksDataStore?
    //  Or separate Repository for every data source?
    private val database: AppDatabase,
    private val network: BooksRemoteDataSource
) {

    //Transforms database entity to domain
    val books: Flow<List<OpenLibraryBook>> =
        database.booksDao().getAllBooks()
            .map { it.asDomainModel() }

    /**
     * This method retrieve data from network and refresh the offline database.
     */
    suspend fun refreshBooks() {
        withContext(Dispatchers.IO) {
            // Safe network response
            var listBooks: List<OpenLibraryBook> = listOf()
            when (val response = network.invoke()) {
                is ApiResult.Success -> listBooks = response.data.docs
                is ApiResult.Error -> Log.e(TAG, "${response.code} ${response.message}")
                is ApiResult.Exception -> Log.e(TAG, "${response.e.message}")
            }
            // Update database
            database.booksDao().insertAll(listBooks.asDatabaseModel())
        }
    }

    suspend fun deleteAllBooks() {
        database.booksDao().deleteAllBooks()
    }
}
