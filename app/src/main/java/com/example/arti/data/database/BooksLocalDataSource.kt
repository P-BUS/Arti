package com.example.arti.data.database

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BooksLocalDataSource @Inject constructor(
    private val database: AppDatabase
) {
    fun getAllBooks(): Flow<List<BooksEntity>> =
        database.booksDao().getAllBooks()

    fun getBook(cover: String): Flow<BooksEntity> =
        database.booksDao().getBook(cover)

    suspend fun insertAll(books: List<BooksEntity>) =
        database.booksDao().insertAll(books)

    suspend fun update(book: BooksEntity) =
        database.booksDao().update(book)

    suspend fun deleteBook(book: BooksEntity) =
        database.booksDao().deleteBook(book)

    suspend fun deleteAllBooks() =
        database.booksDao().deleteAllBooks()
}