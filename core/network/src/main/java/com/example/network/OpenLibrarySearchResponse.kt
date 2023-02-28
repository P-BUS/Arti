package com.example.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OpenLibrarySearchResponse(
    val docs: List<OpenLibraryBook> = listOf(),
    @Json(name = "num_found")
    val numFound: Int = 0,
    val q: String = "",
    val start: Int = 0
)



