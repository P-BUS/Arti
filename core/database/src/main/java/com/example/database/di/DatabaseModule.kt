package com.example.database.di

import android.content.Context
import androidx.room.Room
import com.example.database.AppDatabase
import com.example.database.BooksDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "books_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Module
    @InstallIn(SingletonComponent::class)
    object DaoModule {
        @Provides
        fun provideBooksDao(database: AppDatabase): BooksDao {
            return database.booksDao()
        }
    }
}
