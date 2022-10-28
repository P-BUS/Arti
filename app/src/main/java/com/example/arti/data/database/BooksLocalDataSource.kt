package com.example.arti.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [BooksEntity::class], version = 1, exportSchema = false)
@TypeConverters(ConverterString::class, ConverterInt::class)
abstract class BooksLocalDataSource : RoomDatabase() {
    abstract fun booksDao(): BooksDao

    companion object {
        @Volatile //The value of a volatile variable will never be cached, and all writes and reads will be done to and from the main memory.
        private var INSTANCE: BooksLocalDataSource? = null

        fun getLocalDataSource(context: Context): BooksLocalDataSource {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BooksLocalDataSource::class.java,
                    "books_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}