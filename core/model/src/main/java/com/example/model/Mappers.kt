package com.example.arti.utils

import com.example.database.BooksEntity
import com.example.network.OpenLibraryBook


/**
 * Map [BooksEntity] to domain entities
 */
fun List<com.example.database.BooksEntity>.asDomainModel(): List<com.example.model.Book> {
    return map {
        com.example.model.Book(
            key = it.key,
            authorName = it.authorName,
            coverI = it.coverI,
            language = it.language,
            title = it.title,
            type = it.type
        )
    }
}

/**
 * Map [OpenLibraryBook] to database entities
 */
fun List<com.example.network.OpenLibraryBook>.asDatabaseModel(): List<com.example.database.BooksEntity> {
    return map {
        com.example.database.BooksEntity(
            key = it.key,
            authorName = it.authorName,
            coverI = it.coverI,
            language = it.language,
            title = it.title,
            type = it.type
        )
    }
}

