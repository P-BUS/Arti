package com.example.arti.worker

import android.app.Notification
import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import com.example.arti.ui.viewmodel.BooksViewModel

const val NOTIFICATION_ID = 1

class BooksUpdateWorker(context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams) {

    override suspend fun getForegroundInfo(): ForegroundInfo {
        return ForegroundInfo(
            NOTIFICATION_ID, createNotification()
        )
    }
    override suspend fun doWork(): Result {
        try {
            // Put my code to execute here
        } catch (exception: Exception) {
            return Result.failure()
        }
        return Result.success()

    }
    private fun createNotification() : Notification {
        TODO()
    }
}