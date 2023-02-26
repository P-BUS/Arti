package com.example.arti.data.network

import com.example.arti.utils.Constants
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


// TODO: decrease quantity of received properties and change it as needed for UI in Mappers
@JsonClass(generateAdapter = true)
data class OpenLibraryBook(

    @Json(name = "author_name")
    val authorName: List<String> = listOf(Constants.MISSED_STRING_DATA),
    @Json(name = "cover_edition_key")
    val coverEditionKey: String = Constants.MISSED_STRING_DATA,
    @Json(name = "cover_i")
    val coverI: Int = Constants.MISSED_INT_DATA,
    @Json(name = "ebook_access")
    val ebookAccess: String = Constants.MISSED_STRING_DATA,
    @Json(name = "ebook_count_i")
    val ebookCountI: Int = Constants.MISSED_INT_DATA,
    @Json(name = "edition_count")
    val editionCount: Int = Constants.MISSED_INT_DATA,
    @Json(name = "edition_key")
    val editionKey: List<String> = listOf(Constants.MISSED_STRING_DATA),
    @Json(name = "first_publish_year")
    val firstPublishYear: Int = Constants.MISSED_INT_DATA,
    @Json(name = "has_fulltext")
    val hasFulltext: Boolean = Constants.MISSED_BOOLEAN_DATA,
    val ia: List<String> = listOf(Constants.MISSED_STRING_DATA),
    @Json(name = "ia_collection_s")
    val iaCollectionS: String = Constants.MISSED_STRING_DATA,
    val key: String = Constants.MISSED_STRING_DATA,
    val language: List<String> = listOf(Constants.MISSED_STRING_DATA),
    @Json(name = "last_modified_i")
    val lastModifiedI: Int = Constants.MISSED_INT_DATA,
    @Json(name = "publicScanB")
    val public_scan_b: Boolean = Constants.MISSED_BOOLEAN_DATA,
    @Json(name = "publish_date")
    val publishDate: List<String> = listOf(Constants.MISSED_STRING_DATA),
    @Json(name = "publish_year")
    val publishYear: List<Int> = listOf(Constants.MISSED_INT_DATA),
    @Json(name = "total_count")
    val seed: List<String> = listOf(Constants.MISSED_STRING_DATA),
    val subject: List<String> = listOf(Constants.MISSED_STRING_DATA),
    @Json(name = "subject_facet")
    val subjectFacet: List<String> = listOf(Constants.MISSED_STRING_DATA),
    @Json(name = "subject_key")
    val subjectKey: List<String> = listOf(Constants.MISSED_STRING_DATA),
    val title: String = Constants.MISSED_STRING_DATA,
    @Json(name = "title_suggest")
    val titleSuggest: String = Constants.MISSED_STRING_DATA,
    val type: String = Constants.MISSED_STRING_DATA
)

