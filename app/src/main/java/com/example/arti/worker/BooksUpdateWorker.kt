package com.example.arti.worker

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.arti.data.repository.BooksRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

const val TAG = "BookUpdateWorker"

// TODO: Add Hilt DI
@HiltWorker
class SyncBooksWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val booksRepository: BooksRepository
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            // TODO: Change hardcoded parameter
            booksRepository.refreshBooks("Ukraine")
            Log.e(TAG, "WorkManager started books sync")
            Result.success()
        } catch (exception: Exception) {
            Log.e(TAG, "WorkManager error in Periodic work", exception)
            Result.failure()
        }
    }
}