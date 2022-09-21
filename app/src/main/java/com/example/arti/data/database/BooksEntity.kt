package com.example.arti.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.arti.data.model.OpenLibraryBook

@Entity(tableName = "books_database")
data class BooksEntity(
    @PrimaryKey
    val name: String,
    val type: String,
    val description: String
)

/**
 * Map [BooksEntity] to domain entities
 */
fun List<BooksEntity>.asDomainModel(): List<OpenLibraryBook> {
    return map {
        OpenLibraryBook(
            name = it.name,
            type = it.type,
            description = it.description)
    }
}