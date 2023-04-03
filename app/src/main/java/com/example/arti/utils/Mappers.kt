package com.example.arti.utils

import com.example.arti.data.database.BooksEntity
import com.example.arti.data.model.Book
import com.example.arti.data.network.OpenLibraryBook


/**
 * Map [BooksEntity] to domain entities
 */
fun List<BooksEntity>.asDomainModel(): List<Book> {
    return map {
        Book(
            key = it.key,
            authorName = it.authorName,
            coverI = it.coverI,
            language = it.language,
            title = it.title,
            type = it.type,
            ia = it.ia
        )
    }
}

/**
 * Map [OpenLibraryBook] to database entities
 */
fun List<OpenLibraryBook>.asDatabaseModel(): List<BooksEntity> {
    return map {
        BooksEntity(
            key = it.key,
            authorName = it.authorName ?: listOf("Unknown Author"),
            coverI = it.coverI,
            language = it.language,
            title = it.title,
            type = it.type,
            ia = it.ia[0]
        )
    }
}

