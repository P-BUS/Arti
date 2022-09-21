package com.example.arti.ui.viewmodel

import com.example.arti.data.model.OpenLibraryBook
import androidx.lifecycle.*
import com.example.arti.data.database.BooksDao
import com.example.arti.data.database.BooksDatabase
import com.example.arti.data.database.BooksEntity
import com.example.arti.data.network.BooksApi
import kotlinx.coroutines.launch
import java.lang.Exception

enum class BooksApiStatus { LOADING, ERROR, DONE }


class BooksViewModel(private val booksDao: BooksDao
) : ViewModel() {

    private val _allBooks: LiveData<List<BooksEntity>> = booksDao.getAllBooks().asLiveData()
    val allBooks: LiveData<List<BooksEntity>> = _allBooks

    fun retrieveBook(name: String): LiveData<BooksEntity> {
        return booksDao.getBook(name).asLiveData()
    }

fun deleteBook(book: BooksEntity) {
    viewModelScope.launch {
        booksDao.delete(book)
    }
}

    private var _openLibraryBooks = MutableLiveData<List<OpenLibraryBook>>()
    val openLibraryBooks: LiveData<List<OpenLibraryBook>> = _openLibraryBooks

    private val _currentBook = MutableLiveData<OpenLibraryBook>()
    val currentBook: LiveData<OpenLibraryBook> = _currentBook

    private val _status = MutableLiveData<BooksApiStatus>()
    val status: LiveData<BooksApiStatus> = _status

 //   init {
 //        //Initialize the books on start search.
 //        getOpenLibrarySearchResponse()
 //   }

    fun getOpenLibrarySearchResponse() {
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
                // TODO: Dont understand yet how to back unsuccessful Response from Retrofit API
                //_openLibrarySearchResponse.value =
            }
        }
    }

    // Updates current book LiveData property
    fun updateCurrentBook(book: OpenLibraryBook) {
        _currentBook.value = book
    }
}

class BooksViewModelFactory(private val booksDao: BooksDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BooksViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BooksViewModel(booksDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
