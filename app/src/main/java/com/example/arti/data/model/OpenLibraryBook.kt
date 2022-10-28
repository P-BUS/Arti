package com.example.arti.data.model

import com.example.arti.other.Constants


data class OpenLibraryBook(
    val _version_: Long = Constants.MISSED_LONG_DATA,
    val author_alternative_name: List<String> = listOf(Constants.MISSED_STRING_DATA),
    val author_facet: List<String> = listOf(Constants.MISSED_STRING_DATA),
    val author_key: List<String> = listOf(Constants.MISSED_STRING_DATA),
    val author_name: List<String> = listOf(Constants.MISSED_STRING_DATA),
    val cover_edition_key: String = Constants.MISSED_STRING_DATA,
    val cover_i: Int = Constants.MISSED_INT_DATA,
    val ebook_access: String = Constants.MISSED_STRING_DATA,
    val ebook_count_i: Int = Constants.MISSED_INT_DATA,
    val edition_count: Int = Constants.MISSED_INT_DATA,
    val edition_key: List<String> = listOf(Constants.MISSED_STRING_DATA),
    val first_publish_year: Int = Constants.MISSED_INT_DATA,
    val has_fulltext: Boolean = Constants.MISSED_BOOLEAN_DATA,
    val ia: List<String> = listOf(Constants.MISSED_STRING_DATA),
    val ia_collection_s: String = Constants.MISSED_STRING_DATA,
    val key: String = Constants.MISSED_STRING_DATA,
    val language: List<String> = listOf(Constants.MISSED_STRING_DATA),
    val last_modified_i: Int = Constants.MISSED_INT_DATA,
    val public_scan_b: Boolean = Constants.MISSED_BOOLEAN_DATA,
    val publish_date: List<String> = listOf(Constants.MISSED_STRING_DATA),
    val publish_year: List<Int> = listOf(Constants.MISSED_INT_DATA),
    val seed: List<String> = listOf(Constants.MISSED_STRING_DATA),
    val subject: List<String> = listOf(Constants.MISSED_STRING_DATA),
    val subject_facet: List<String> = listOf(Constants.MISSED_STRING_DATA),
    val subject_key: List<String> = listOf(Constants.MISSED_STRING_DATA),
    val title: String = Constants.MISSED_STRING_DATA,
    val title_suggest: String = Constants.MISSED_STRING_DATA,
    val type: String = Constants.MISSED_STRING_DATA
)

