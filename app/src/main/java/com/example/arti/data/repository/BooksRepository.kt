package com.example.arti.data.repository

import android.util.Log
import com.example.arti.data.database.BooksLocalDataSource
import com.example.arti.data.model.Book
import com.example.arti.data.network.ApiResult
import com.example.arti.data.network.BooksRemoteDataSource
import com.example.arti.data.network.OpenLibraryBook
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
    private val database: BooksLocalDataSource,
    private val network: BooksRemoteDataSource
) {

    //Transforms database entity to domain
    val booksStream: Flow<List<Book>> =
        database.getAllBooks()
            .map { it.asDomainModel() }

    /**
     * This method retrieve data from network and refresh the offline database.
     */
    suspend fun refreshBooks() {
        withContext(Dispatchers.IO) {
            // Safe network response
            var listBooks: List<OpenLibraryBook> = listOf()
            when (val response = network.invoke()) {
                is ApiResult.Success -> {
                    deleteAllBooks()
                    listBooks = response.data.docs
                }
                is ApiResult.Error -> Log.e(TAG, "${response.code} ${response.message}")
                is ApiResult.Exception -> Log.e(TAG, "${response.e.message}")
            }
            // Update database
            database.insertAll(listBooks.asDatabaseModel())
        }
    }

    suspend fun deleteAllBooks() {
        database.deleteAllBooks()
    }
}
