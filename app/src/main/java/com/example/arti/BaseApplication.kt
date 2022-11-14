package com.example.arti

import android.app.Application
import com.example.arti.data.database.BooksLocalDataSource
import dagger.hilt.android.HiltAndroidApp

/**
 * An application class that inherits from [Application], allows for the creation of a singleton
 * instance of the [BooksLocalDataSource]
 */
@HiltAndroidApp
class BaseApplication : Application() {
    //val database: BooksLocalDataSource by lazy { BooksLocalDataSource.getLocalDataSource(this) }
}