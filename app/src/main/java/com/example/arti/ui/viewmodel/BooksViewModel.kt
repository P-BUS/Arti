package com.example.arti.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.data.repository.BooksRepository
import com.example.data.repository.LayoutRepository
import com.example.model.Book
import com.example.work.worker.SyncBooksWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject


enum class BooksApiStatus { LOADING, ERROR, DONE }

const val TAG = "BooksViewModel"
const val WORKER_TAG = "WorkManager syncBooks"

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val booksRepository: com.example.data.repository.BooksRepository,
    private val layoutRepository: com.example.data.repository.LayoutRepository,
    private val workManager: WorkManager
) : ViewModel() {

    //val workManager = WorkManager.getInstance(application)

    // Transforms books Flow to StateFow with and retrying to fetch data if IO Exceptions
    val books: StateFlow<List<com.example.model.Book>> =
        booksRepository.booksStream
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

    private val _currentBook = MutableSharedFlow<com.example.model.Book>(
        replay = 1,
        extraBufferCapacity = 0,
        BufferOverflow.DROP_OLDEST
    )
    val currentBook: SharedFlow<com.example.model.Book> = _currentBook.asSharedFlow()

    // Store the status of updating database from web service
    private val _status = MutableStateFlow(BooksApiStatus.DONE)
    val status: StateFlow<BooksApiStatus> = _status.asStateFlow()

    init {
        refreshDataFromRepository()
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
            PeriodicWorkRequestBuilder<com.example.work.worker.SyncBooksWorker>(5, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .build()

        workManager.enqueue(scheduleWorkRequest) // Enqueue the work request
        // TODO: need to add observer to this work info
        workManager.getWorkInfosByTag(WORKER_TAG)// Produce work info by tag
    }

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            _status.value = BooksApiStatus.LOADING
            try {
                booksRepository.refreshBooks()
                _status.value = BooksApiStatus.DONE
            } catch (networkError: IOException) {
                _status.value = BooksApiStatus.ERROR
                Log.e(TAG, "IO Exception $networkError, you might not have internet connection")
            }
        }
    }

    // Updates current book property
    fun updateCurrentBook(book: com.example.model.Book) {
        viewModelScope.launch {
            _currentBook.emit(book)
        }
    }

    // Write to a Preferences DataStore
    suspend fun saveLayoutToPreferencesStore(isLinearLayoutManager: Boolean) =
        layoutRepository.saveLayoutToPreferencesStore(isLinearLayoutManager)
}


