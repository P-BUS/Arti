package com.example.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.model.Book


@Entity(tableName = "books_database")
data class BooksEntity(
    @PrimaryKey
    val key: String,

    @ColumnInfo(name = "author_name")
    val authorName: List<String>,

    @ColumnInfo(name = "cover_i")
    val coverI: Int,

    @ColumnInfo(name = "language")
    val language: List<String>,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "type")
    val type: String
)

fun List<BooksEntity>.asDomainModel(): List<Book> {
    return map {
        Book(
            key = it.key,
            authorName = it.authorName,
            coverI = it.coverI,
            language = it.language,
            title = it.title,
            type = it.type
        )
    }
}

