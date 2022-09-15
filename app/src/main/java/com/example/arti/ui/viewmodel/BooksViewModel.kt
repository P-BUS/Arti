package com.example.arti.ui.viewmodel

import OpenLibraryBook
import androidx.lifecycle.*
import com.example.arti.data.network.BooksApi
import kotlinx.coroutines.launch
import java.lang.Exception

enum class BooksApiStatus { LOADING, ERROR, DONE }


class OrderViewModel() : ViewModel() {

    //private lateinit var _openLibrarySearchResponse : OpenLibrarySearchResponse
    //val openLibrarySearchResponse: LiveData<OpenLibrarySearchResponse> = _openLibrarySearchResponse

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
