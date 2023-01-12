package com.example.arti.worker

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.arti.data.repository.BooksRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

const val TAG = "WorkManager SyncBooksWorker"

@HiltWorker
class SyncBooksWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val booksRepository: BooksRepository
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result =
        withContext(Dispatchers.IO) {
            try {
                // TODO: Seems it right but don't work))
                // TODO: Change hardcoded parameter
                booksRepository.refreshBooks("Ukraine")
                Log.i(TAG, "WorkManager started books sync")
                Result.success()
            } catch (exception: Exception) {
                Log.e(TAG, "WorkManager error in Periodic work", exception)
                exception.printStackTrace()
                Result.failure()
            }
        }
}