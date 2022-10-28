package com.example.arti.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.arti.data.database.BooksLocalDataSource.Companion.getLocalDataSource
import com.example.arti.data.model.OpenLibraryBook
import com.example.arti.data.repository.BooksRepository
import com.example.arti.data.repository.LayoutRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException


enum class BooksApiStatus { LOADING, ERROR, DONE }

const val TAG = "BooksViewModel"

class BooksViewModel(
    application: Application,
    private val booksRepository: BooksRepository,
    layoutRepository: LayoutRepository
) : AndroidViewModel(application) {

    //private val booksRepository = BooksRepository(getLocalDataSource(application))

    val books = booksRepository.books

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
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            _status.value = BooksApiStatus.LOADING
            try {
                booksRepository.refreshBooks()
                _status.value = BooksApiStatus.DONE
            } catch (networkError: IOException) {
                _status.value = BooksApiStatus.ERROR
                Log.e(TAG, "IO Exception, you might not have internet connection")
            }
        }
    }

    // Updates current book LiveData property
    fun updateCurrentBook(book: OpenLibraryBook) {
        _currentBook.value = book
    }
}

class BooksViewModelFactory(
    private val application: Application,
    private val booksRepository: BooksRepository,
    private val layoutRepository: LayoutRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BooksViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BooksViewModel(application, booksRepository, layoutRepository) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}
