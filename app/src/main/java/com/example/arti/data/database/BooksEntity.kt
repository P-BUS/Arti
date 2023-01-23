package com.example.arti.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "books_database")
data class BooksEntity(
    @PrimaryKey
    val key: String,

    @ColumnInfo(name = "_version_")
    val version: Long,

    @ColumnInfo(name = "author_alternative_name")
    val authorAlternativeName: List<String>,

    @ColumnInfo(name = "author_facet")
    val authorFacet: List<String>,

    @ColumnInfo(name = "author_key")
    val authorKey: List<String>,

    @ColumnInfo(name = "author_name")
    val authorName: List<String>,

    @ColumnInfo(name = "cover_edition_key")
    val coverEditionKey: String,

    @ColumnInfo(name = "cover_i")
    val coverI: Int,

    @ColumnInfo(name = "ebook_access")
    val ebookAccess: String,

    @ColumnInfo(name = "ebook_count_i")
    val ebookCountI: Int,

    @ColumnInfo(name = "has_fulltext")
    val hasFulltext: Boolean,

    @ColumnInfo(name = "ia")
    val ia: List<String>,

    @ColumnInfo(name = "ia_collection_s")
    val iaCollectionS: String,

    @ColumnInfo(name = "language")
    val language: List<String>,

    @ColumnInfo(name = "seed")
    val seed: List<String>,

    @ColumnInfo(name = "subject")
    val subject: List<String>,

    @ColumnInfo(name = "subject_facet")
    val subjectFacet: List<String>,

    @ColumnInfo(name = "subject_key")
    val subjectKey: List<String>,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "title_suggest")
    val titleSuggest: String,

    @ColumnInfo(name = "type")
    val type: String
)

