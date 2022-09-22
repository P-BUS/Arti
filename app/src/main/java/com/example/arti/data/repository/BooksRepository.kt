package com.example.arti.data.repository

import android.view.animation.Transformation
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.asLiveData
import com.example.arti.data.database.BooksDatabase
import com.example.arti.data.database.asDomainModel
import com.example.arti.data.model.OpenLibraryBook
import com.example.arti.data.network.BooksApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class BooksRepository(private val database: BooksDatabase) {


    val books: LiveData<List<OpenLibraryBook>> = Transformations.map(database.booksDao().getAllBooks().asLiveData()) {
        it.asDomainModel()
    }


    /**
     * This method the API used to refresh the offline cache.
     */
    suspend fun refreshBooks() {
        withContext(Dispatchers.IO) {
            val booksList = BooksApi.retrofitApiService.getSearchBooks()
            database.booksDao().getAllBooks()
        }
    }
}
