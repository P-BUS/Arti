package com.example.network

import com.example.database.model.BooksEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class OpenLibraryBook(

    @Json(name = "author_name")
    val authorName: List<String> = listOf(),
    @Json(name = "cover_i")
    val coverI: Int = 0,
    val key: String = "",
    val language: List<String> = listOf(),
    val title: String = "",
    val type: String = ""
)

fun List<OpenLibraryBook>.asDatabaseModel(): List<BooksEntity> {
    return map {
        BooksEntity(
            key = it.key,
            authorName = it.authorName,
            coverI = it.coverI,
            language = it.language,
            title = it.title,
            type = it.type
        )
    }
}

