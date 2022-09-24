package com.example.arti.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.arti.data.model.OpenLibraryBook

@Entity(tableName = "books_database")
data class BooksEntity(
    @PrimaryKey
    val name: String,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "description")
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

/**
 * Map [OpenLibraryBook] to database entities
 */
fun List<OpenLibraryBook>.asDatabaseModel(): List<BooksEntity> {
    return map {
        BooksEntity(
            name = it.name,
            type = it.type,
            description = it.description)
    }
}