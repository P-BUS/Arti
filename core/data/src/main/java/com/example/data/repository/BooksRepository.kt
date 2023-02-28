package com.example.data.repository

import android.util.Log
import com.example.database.BooksLocalDataSource
import com.example.model.Book
import com.example.model.asDatabaseModel
import com.example.model.asDomainModel
import com.example.network.BooksRemoteDataSource
import com.example.network.OpenLibraryBook
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
                is com.example.network.ApiResult.Success -> {
                    deleteAllBooks()
                    listBooks = response.data.docs
                }
                is com.example.network.ApiResult.Error -> Log.e(TAG, "${response.code} ${response.message}")
                is com.example.network.ApiResult.Exception -> Log.e(TAG, "${response.e.message}")
            }
            // Update database
            database.insertAll(listBooks.asDatabaseModel())
        }
    }

    suspend fun deleteAllBooks() {
        database.deleteAllBooks()
    }
}
