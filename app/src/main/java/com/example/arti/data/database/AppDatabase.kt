package com.example.arti.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.arti.Utils.ConverterInt
import com.example.arti.Utils.ConverterString

@Database(entities = [BooksEntity::class], version = 1, exportSchema = false)
@TypeConverters(ConverterString::class, ConverterInt::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun booksDao(): BooksDao
}