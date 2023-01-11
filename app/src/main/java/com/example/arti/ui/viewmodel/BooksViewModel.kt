package com.example.arti.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.arti.data.model.OpenLibraryBook
import com.example.arti.data.repository.BooksRepository
import com.example.arti.data.repository.LayoutRepository
import com.example.arti.worker.SyncBooksWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject


enum class BooksApiStatus { LOADING, ERROR, DONE }

const val TAG = "BooksViewModel"
const val WORKER_TAG = "syncBooksWorkManager"

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val booksRepository: BooksRepository,
    private val layoutRepository: LayoutRepository,
    private val workManager: WorkManager
) : ViewModel() {

    //val workManager = WorkManager.getInstance(application)

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

    // TODO: change to StateFlow but what to choose for default value?
    private val _currentBook = MutableLiveData<OpenLibraryBook>()
    val currentBook: LiveData<OpenLibraryBook> = _currentBook

    // Store the status of updating database from web service
    private val _status = MutableStateFlow(BooksApiStatus.DONE)
    val status: StateFlow<BooksApiStatus> = _status.asStateFlow()

    // TODO: To change the hardcoded text to variable or string source
    // Default search when the APP is started
    var searchText: String = "Ukraine"

    init {
        refreshDataFromRepository(searchText)
        scheduleRefreshDataFromRepository()
    }

    // Work manager function
    private fun scheduleRefreshDataFromRepository() {
        // Constraints in which worker will be executed
        val constraints =
            Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

        val scheduleWorkRequest =
            PeriodicWorkRequestBuilder<SyncBooksWorker>(1, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .addTag(WORKER_TAG)
                .build()

        workManager.enqueue(scheduleWorkRequest) // Enqueue the work request
        // TODO: need to add observer to this work info
        workManager.getWorkInfosByTag(WORKER_TAG)// Produce work info by tag
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


