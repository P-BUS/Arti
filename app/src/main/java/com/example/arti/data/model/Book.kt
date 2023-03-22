package com.example.arti.data.model

data class Book(
    val authorName: List<String> = listOf(),
    val coverI: Int = 0,
    val key: String = "",
    val language: List<String> = listOf(),
    val title: String = "",
    val type: String = ""
)
