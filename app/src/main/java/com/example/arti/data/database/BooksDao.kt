package com.example.arti.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface BooksDao {

    @Query("SELECT * FROM books_database")
    fun getAllBooks(): Flow<List<BooksEntity>>

    @Query("SELECT * FROM books_database WHERE name = :name")
    fun getBook(name: String): Flow<BooksEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: BooksEntity)

    @Update
    suspend fun update(book: BooksEntity)

    @Delete
    suspend fun delete(bsook: BooksEntity)
}