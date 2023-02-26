package com.example.arti.data.model


data class Book(
    val version: Long,
    val authorAlternativeName: List<String>,
    val authorFacet: List<String>,
    val authorKey: List<String>,
    val authorName: List<String>,
    val coverEditionKey: String,
    val coverI: Int,
    val ebookAccess: String,
    val ebookCountI: Int,
    val editionCount: Int,
    val editionKey: List<String>,
    val firstPublishYear: Int,
    val hasFulltext: Boolean,
    val ia: List<String>,
    val iaCollectionS: String,
    val key: String,
    val language: List<String>,
    val lastModifiedI: Int,
    val public_scan_b: Boolean,
    val publishDate: List<String>,
    val publishYear: List<Int>,
    val seed: List<String>,
    val subject: List<String>,
    val subjectFacet: List<String>,
    val subjectKey: List<String>,
    val title: String ,
    val titleSuggest: String,
    val type: String
)
