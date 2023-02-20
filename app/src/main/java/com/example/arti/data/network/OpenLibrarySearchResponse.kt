package com.example.arti.data.network

import com.example.arti.data.model.OpenLibraryBook
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OpenLibrarySearchResponse(
    val docs: List<OpenLibraryBook> = listOf(),
    @SerializedName("num_found")
    val numFound: Int = 0,
    val q: String = "",
    val start: Int = 0
)



