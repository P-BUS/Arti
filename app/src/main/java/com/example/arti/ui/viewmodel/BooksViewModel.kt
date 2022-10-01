package com.example.arti.ui.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.arti.data.database.BooksDatabase.Companion.getDatabase
import com.example.arti.data.model.OpenLibraryBook
import com.example.arti.data.repository.BooksRepository
import kotlinx.coroutines.launch
import java.io.IOException


enum class BooksApiStatus { LOADING, ERROR, DONE }


class BooksViewModel(
    application: Application
//    private val booksDao: BooksDao
) : AndroidViewModel(application) {

    private val booksRepository = BooksRepository(getDatabase(application))

    val books = booksRepository.books

 /*   private val _allBooks: LiveData<List<BooksEntity>> = booksDao.getAllBooks().asLiveData()
    val allBooks: LiveData<List<BooksEntity>> = _allBooks

    fun retrieveBook(name: String): LiveData<BooksEntity> {
        return booksDao.getBook(name).asLiveData()
    }

fun deleteBook(book: BooksEntity) {
    viewModelScope.launch {
        booksDao.delete(book)
    }
}*/

    private var _openLibraryBooks = MutableLiveData<List<OpenLibraryBook>>()
    val openLibraryBooks: LiveData<List<OpenLibraryBook>> = _openLibraryBooks

    private val _currentBook = MutableLiveData<OpenLibraryBook>()
    val currentBook: LiveData<OpenLibraryBook> = _currentBook

    private val _status = MutableLiveData<BooksApiStatus>()
    val status: LiveData<BooksApiStatus> = _status

    init {
        refreshDataFromRepository()
    }

    // TODO: Remake error status instead loading waiting
    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            _status.value = BooksApiStatus.LOADING
            try {
                booksRepository.refreshBooks()
                _status.value = BooksApiStatus.DONE
            }
            catch (networkError: IOException) {
                _status.value = BooksApiStatus.ERROR
            }
        }
    }




/*    fun getOpenLibrarySearchResponse() {
       viewModelScope.launch {
            _status.value = BooksApiStatus.LOADING
            try {
                _openLibraryBooks.value = BooksApi.retrofitApiService.getSearchBooks(
//                    "Чорна рада",
//                     "ukr",
//                    "true",
//                   "ebooks"
                )
                //_openLibraryBooks.value = _openLibrarySearchResponse.docs

                _status.value = BooksApiStatus.DONE
            } catch (e: Exception) {
                _status.value = BooksApiStatus.ERROR
                //_openLibrarySearchResponse.value =
            }
        }
    }*/

    // Updates current book LiveData property
    fun updateCurrentBook(book: OpenLibraryBook) {
        _currentBook.value = book
    }

    // Updates current _status LiveData property
    fun updateCurrentStatus(status: BooksApiStatus) {
        _status.value = status
    }

}

class BooksViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BooksViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BooksViewModel(application) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}
