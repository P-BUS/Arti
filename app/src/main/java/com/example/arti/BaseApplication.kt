package com.example.arti

import android.app.Application
import com.example.arti.data.database.BooksLocalDataSource

/**
 * An application class that inherits from [Application], allows for the creation of a singleton
 * instance of the [BooksLocalDataSource]
 */
class BaseApplication : Application() {
    val database: BooksLocalDataSource by lazy { BooksLocalDataSource.getLocalDataSource(this) }
}