package com.example.arti.data.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class OpenLibraryBook(

    @Json(name = "author_name")
    val authorName: List<String>? = null,
    @Json(name = "cover_i")
    val coverI: Int = 0,
    val key: String = "",
    val language: List<String> = listOf(),
    val title: String = "",
    val type: String = "",
    val ia: List<String> = listOf()
)

