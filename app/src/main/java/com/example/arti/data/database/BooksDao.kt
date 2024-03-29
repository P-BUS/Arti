package com.example.arti.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface BooksDao {

    @Query("SELECT * FROM books_database")
    fun getAllBooks(): Flow<List<BooksEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(books: List<BooksEntity>)

    @Update
    suspend fun update(book: BooksEntity)

    @Delete
    suspend fun deleteBook(book: BooksEntity)

    @Query("DELETE FROM books_database")
    suspend fun deleteAllBooks()

}