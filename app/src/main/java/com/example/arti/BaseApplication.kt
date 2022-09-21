package com.example.arti

import android.app.Application
import com.example.arti.data.database.BooksDatabase

/**
 * An application class that inherits from [Application], allows for the creation of a singleton
 * instance of the [BooksDatabase]
 */
class BaseApplication : Application() {
    val database: BooksDatabase by lazy { BooksDatabase.getDatabase(this) }
}