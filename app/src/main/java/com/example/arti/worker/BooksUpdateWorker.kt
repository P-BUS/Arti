package com.example.arti.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class BooksUpdateWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        TODO("Not yet implemented")
    }
}