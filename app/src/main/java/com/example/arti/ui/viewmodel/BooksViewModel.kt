package com.example.arti.ui.viewmodel

import OpenLibraryBook
import OpenLibrarySearchResponse
import android.util.Log
import androidx.lifecycle.*
import com.example.arti.R
import com.example.arti.data.network.BooksApi
import com.example.arti.other.Constants
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception

const val TAG = "ViewModel"
enum class BooksApiStatus { LOADING, ERROR, DONE }


class OrderViewModel() : ViewModel() {

    private var _openLibrarySearchResponse = MutableLiveData<OpenLibrarySearchResponse>()
    val openLibrarySearchResponse: LiveData<OpenLibrarySearchResponse> = _openLibrarySearchResponse

    private var _openLibraryBooks = MutableLiveData<List<OpenLibraryBook>>()
    val openLibraryBooks: LiveData<List<OpenLibraryBook>> = _openLibraryBooks

    private val _currentBook = MutableLiveData<OpenLibraryBook>()
    val currentBook: LiveData<OpenLibraryBook> = _currentBook

    private val _status = MutableLiveData<BooksApiStatus>()
    val status: LiveData<BooksApiStatus> = _status

    init {
        // Initialize the books on start search.
        getOpenLibrarySearchResponse()
    }

    private fun getOpenLibrarySearchResponse() {
        viewModelScope.launch {
            _status.value = BooksApiStatus.LOADING
            try {
                _openLibrarySearchResponse.value = BooksApi.retrofitApiService.getSearchBooks(
                    // TODO: to change hardcoded text to requests from user
                    "Шевченко",
                     "ukr",
                    "true",
                    // TODO: change hardcoded text to string link in res but needs context
                    "ebooks"
                )
                _openLibraryBooks.value = _openLibrarySearchResponse.value!!.docs
                _openLibraryBooks.value!!.size
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
