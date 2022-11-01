package com.example.arti.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.asLiveData

import com.example.arti.data.database.BooksLocalDataSource
import com.example.arti.data.model.OpenLibraryBook
import com.example.arti.data.network.BooksRemoteDataSource
import com.example.arti.other.asDatabaseModel
import com.example.arti.other.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext


class BooksRepository(
    private val database: BooksLocalDataSource,
    private val network: BooksRemoteDataSource
) {

    //Transforms database entity to domain
    // TODO: Transform to Flow
    val books: Flow<List<OpenLibraryBook>> =
        database.booksDao().getAllBooks()
            .map { it.asDomainModel() }

    /**
     * This method retrieve data from network and refresh the offline database.
     */
    suspend fun refreshBooks(searchText: String) {
        withContext(Dispatchers.IO) {
            // TODO: add handle IO Exceptions
            val searchResult = network.retrofitApiService.getSearchBooks(
                searchText = searchText
            )
            val listBooks: List<OpenLibraryBook> = searchResult.docs
            database.booksDao().insertAll(listBooks.asDatabaseModel())
        }
    }
}
