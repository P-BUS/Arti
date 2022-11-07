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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.IOException
import kotlin.coroutines.CoroutineContext


enum class BooksApiStatus { LOADING, ERROR, DONE }

const val TAG = "BooksViewModel"

class BooksViewModel(
    private val booksRepository: BooksRepository,
    private val layoutRepository: LayoutRepository
) : ViewModel() {

    // Transforms books Flow to StateFow with and retrying to fetch data if IO Exceptions
    val books: StateFlow<List<OpenLibraryBook>> =
        booksRepository.books
            // if exception caught retry 3 times on any IOException but also introduce delay 1sec if retrying
            .retry(3) { e ->
                (e is IOException).also { if (it) delay(1000) }
            }
            // transforms from cold Flow to hot StateFlow
            .stateIn(
                scope = viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                initialValue = listOf()
            )

    // Store the information about layout type saved by user
    val isLinearLayout: StateFlow<Boolean> =
        layoutRepository.layoutTypeStream
            // if exception caught retry 3 times on any IOException but also introduce delay 1sec if retrying
            .retry(3) { e ->
                (e is IOException).also { if (it) delay(1000) }
            }
            // transforms from cold Flow to hot StateFlow
            .stateIn(
                scope = viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                initialValue = true
            )

    private val _currentBook = MutableLiveData<OpenLibraryBook>()
    val currentBook: LiveData<OpenLibraryBook> = _currentBook

    // Store the status of updating database from web service
    private val _status = MutableStateFlow(BooksApiStatus.DONE)
    val status: StateFlow<BooksApiStatus> = _status.asStateFlow()

    // Default search when the APP is started
    var searchText: String = "Ukraine"

    init {
        refreshDataFromRepository(searchText)
    }

    private fun refreshDataFromRepository(searchText: String) {
        viewModelScope.launch {
            _status.value = BooksApiStatus.LOADING
            try {
                booksRepository.refreshBooks(searchText)
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

    // TODO: Fun was passed throughout layers without changes only to keep data flow in a right direction
    // Write to a Preferences DataStore
    suspend fun saveLayoutToPreferencesStore(isLinearLayoutManager: Boolean) =
        layoutRepository.saveLayoutToPreferencesStore(isLinearLayoutManager)
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


