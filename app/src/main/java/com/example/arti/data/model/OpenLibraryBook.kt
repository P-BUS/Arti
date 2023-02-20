package com.example.arti.data.model

import com.example.arti.utils.Constants
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OpenLibraryBook(
    @SerializedName("_version_")
    val version: Long = Constants.MISSED_LONG_DATA,
    @SerializedName("author_alternative_name")
    val authorAlternativeName: List<String> = listOf(Constants.MISSED_STRING_DATA),
    @SerializedName("author_facet")
    val authorFacet: List<String> = listOf(Constants.MISSED_STRING_DATA),
    @SerializedName("author_key")
    val authorKey: List<String> = listOf(Constants.MISSED_STRING_DATA),
    @SerializedName("author_name")
    val authorName: List<String> = listOf(Constants.MISSED_STRING_DATA),
    @SerializedName("cover_edition_key")
    val coverEditionKey: String = Constants.MISSED_STRING_DATA,
    @SerializedName("cover_i")
    val coverI: Int = Constants.MISSED_INT_DATA,
    @SerializedName("ebook_access")
    val ebookAccess: String = Constants.MISSED_STRING_DATA,
    @SerializedName("ebook_count_i")
    val ebookCountI: Int = Constants.MISSED_INT_DATA,
    @SerializedName("edition_count")
    val editionCount: Int = Constants.MISSED_INT_DATA,
    @SerializedName("edition_key")
    val editionKey: List<String> = listOf(Constants.MISSED_STRING_DATA),
    @SerializedName("first_publish_year")
    val firstPublishYear: Int = Constants.MISSED_INT_DATA,
    @SerializedName("has_fulltext")
    val hasFulltext: Boolean = Constants.MISSED_BOOLEAN_DATA,
    val ia: List<String> = listOf(Constants.MISSED_STRING_DATA),
    @SerializedName("ia_collection_s")
    val iaCollectionS: String = Constants.MISSED_STRING_DATA,
    val key: String = Constants.MISSED_STRING_DATA,
    val language: List<String> = listOf(Constants.MISSED_STRING_DATA),
    @SerializedName("last_modified_i")
    val lastModifiedI: Int = Constants.MISSED_INT_DATA,
    @SerializedName("publicScanB")
    val public_scan_b: Boolean = Constants.MISSED_BOOLEAN_DATA,
    @SerializedName("publish_date")
    val publishDate: List<String> = listOf(Constants.MISSED_STRING_DATA),
    @SerializedName("publish_year")
    val publishYear: List<Int> = listOf(Constants.MISSED_INT_DATA),
    @SerializedName("total_count")
    val seed: List<String> = listOf(Constants.MISSED_STRING_DATA),
    val subject: List<String> = listOf(Constants.MISSED_STRING_DATA),
    @SerializedName("subject_facet")
    val subjectFacet: List<String> = listOf(Constants.MISSED_STRING_DATA),
    @SerializedName("subject_key")
    val subjectKey: List<String> = listOf(Constants.MISSED_STRING_DATA),
    val title: String = Constants.MISSED_STRING_DATA,
    @SerializedName("title_suggest")
    val titleSuggest: String = Constants.MISSED_STRING_DATA,
    val type: String = Constants.MISSED_STRING_DATA
)

