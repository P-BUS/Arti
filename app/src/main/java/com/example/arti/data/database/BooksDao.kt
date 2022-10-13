package com.example.arti.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface BooksDao {

    @Query("SELECT * FROM books_database")
    fun getAllBooks(): Flow<List<BooksEntity>>

    @Query("SELECT * FROM books_database WHERE cover_edition_key = :cover")
    fun getBook(cover: String): Flow<BooksEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(books: List<BooksEntity>)

    @Update
    suspend fun update(book: BooksEntity)

    @Delete
    suspend fun deleteAll()

    @Delete
    suspend fun deleteBook(book: BooksEntity)
}