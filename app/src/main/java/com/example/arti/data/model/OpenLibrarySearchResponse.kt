package com.example.arti.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OpenLibrarySearchResponse(
    val docs: List<OpenLibraryBook>,
    val numFound: Int,
    //val numFoundExact: Boolean,
    val num_found: Int,
    //val offset: Any,
    val q: String,
    val start: Int
)



