package com.example.arti.data.repository

import com.example.arti.data.database.AppDatabase
import com.example.arti.data.model.OpenLibraryBook
import com.example.arti.utils.asDatabaseModel
import com.example.arti.utils.asDomainModel
import com.example.arti.data.network.BooksApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BooksRepository @Inject constructor(
    private val database: AppDatabase,
    private val network: BooksApiService
) {

    //Transforms database entity to domain
    val books: Flow<List<OpenLibraryBook>> =
        database.booksDao().getAllBooks()
            .map { it.asDomainModel() }

    /**
     * This method retrieve data from network and refresh the offline database.
     */
    suspend fun refreshBooks(searchText: String) {
        withContext(Dispatchers.IO) {
            // TODO: implement safe response with exceptions handling
            val searchResult = network.getSearchBooks(
                searchText = searchText
            )
            val listBooks: List<OpenLibraryBook> = searchResult.docs
            database.booksDao().insertAll(listBooks.asDatabaseModel())
        }
    }
}
