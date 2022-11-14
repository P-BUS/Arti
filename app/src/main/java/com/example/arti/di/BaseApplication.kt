package com.example.arti.di

import android.app.Application
import com.example.arti.data.database.BooksLocalDataSource
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application() {}