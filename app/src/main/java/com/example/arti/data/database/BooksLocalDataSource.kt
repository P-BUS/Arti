package com.example.arti.data.database

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BooksLocalDataSource @Inject constructor(
    private val database: BooksDao
) {
    fun getAllBooks(): Flow<List<BooksEntity>> =
        database.getAllBooks()

    suspend fun insertAll(books: List<BooksEntity>) =
        database.insertAll(books)

    suspend fun update(book: BooksEntity) =
        database.update(book)

    suspend fun deleteBook(book: BooksEntity) =
        database.deleteBook(book)

    suspend fun deleteAllBooks() =
        database.deleteAllBooks()
}