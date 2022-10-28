package com.example.arti.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.asLiveData
import com.example.arti.data.database.BooksLocalDataSource
import com.example.arti.data.database.asDatabaseModel
import com.example.arti.data.database.asDomainModel
import com.example.arti.data.model.OpenLibraryBook
import com.example.arti.data.network.BooksRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class BooksRepository(
    private val database: BooksLocalDataSource
) {

    //Transforms database entity to domain
    val books: LiveData<List<OpenLibraryBook>> =
        Transformations.map(database.booksDao().getAllBooks().asLiveData()) {
            it.asDomainModel()
        }

    /**
     * This method the API used to refresh the offline cache.
     */
    // TODO: Implementation in repository?
    suspend fun refreshBooks() {
        withContext(Dispatchers.IO) {
            val searchResult = BooksRemoteDataSource.retrofitApiService.getSearchBooks(
                searchText = "Ukraine",
                booksLanguage = "ukr",
                hasFullText = "true",
                typeOfDocument = "ebooks"
            )
            val listBooks: List<OpenLibraryBook> = searchResult.docs
            database.booksDao().insertAll(listBooks.asDatabaseModel())
        }
    }
}
