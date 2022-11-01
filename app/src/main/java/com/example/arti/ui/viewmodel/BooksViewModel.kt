package com.example.arti.ui.viewmodel

import android.app.Application
import android.util.Log
import android.util.Log.ERROR
import androidx.lifecycle.*
import com.example.arti.data.database.BooksLocalDataSource
import com.example.arti.data.database.BooksLocalDataSource.Companion.getLocalDataSource
import com.example.arti.data.datastore.LocalDataSource
import com.example.arti.data.model.OpenLibraryBook
import com.example.arti.data.repository.BooksRepository
import com.example.arti.data.repository.LayoutRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.IOException


enum class BooksApiStatus { LOADING, ERROR, DONE }

const val TAG = "BooksViewModel"

class BooksViewModel(
    private val booksRepository: BooksRepository,
    private val layoutRepository: LayoutRepository
) : ViewModel() {

    // TODO: Transform from Flow to StateFow
    val books: Flow<List<OpenLibraryBook>> = booksRepository.books
        // if exception caught retry 3 times on any IOException but also introduce delay 1sec if retrying
        .retry(3) { e ->
            (e is IOException).also { if (it) delay(1000) }
        }

    // Store the information about layout type saved by user
    val isLinearLayout = layoutRepository.layoutTypeStream.asLiveData()

    private val _currentBook = MutableLiveData<OpenLibraryBook>()
    val currentBook: LiveData<OpenLibraryBook> = _currentBook

    /**
     * Store the status of updating database from web service
     */
    private val _status = MutableStateFlow(BooksApiStatus.DONE)
    val status: StateFlow<BooksApiStatus> = _status.asStateFlow()

    init {
        refreshDataFromRepository(
            "Ukraine",
            "ukr",
            "true",
            "ebooks"
        )
    }

    private fun refreshDataFromRepository(
        searchText: String,
        booksLanguage: String,
        hasFullText: String,
        typeOfDocument: String
    ) {
        viewModelScope.launch {
            _status.value = BooksApiStatus.LOADING
            try {
                booksRepository.refreshBooks(searchText, booksLanguage, hasFullText, typeOfDocument)
                _status.value = BooksApiStatus.DONE
            } catch (networkError: IOException) {
                _status.value = BooksApiStatus.ERROR
                Log.e(TAG, "IO Exception $networkError, you might not have internet connection")
            }
        }
    }

    // Updates current book LiveData property
    fun updateCurrentBook(book: OpenLibraryBook) {
        _currentBook.value = book
    }
}

class BooksViewModelFactory(
    //private val application: Application,
    private val booksRepository: BooksRepository,
    private val layoutRepository: LayoutRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BooksViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BooksViewModel(
                booksRepository,
                layoutRepository
            ) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}


