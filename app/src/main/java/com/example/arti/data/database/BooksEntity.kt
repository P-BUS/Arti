package com.example.arti.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


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
    val type: String,

    @ColumnInfo(name = "ia")
    val ia: String
)

