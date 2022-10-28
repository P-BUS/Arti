package com.example.arti.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.arti.data.model.OpenLibraryBook
import com.google.gson.Gson


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

class ConverterString {
    @TypeConverter
    fun listToJson(value: List<String>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()
}

class ConverterInt {
    @TypeConverter
    fun listToJson(value: List<Int>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Int>::class.java).toList()
}

/**
 * Map [BooksEntity] to domain entities
 */
fun List<BooksEntity>.asDomainModel(): List<OpenLibraryBook> {
    return map {
        OpenLibraryBook(
            key = it.key,
            _version_ = it.version,
            author_alternative_name = it.authorAlternativeName,
            author_facet = it.authorFacet,
            author_key = it.authorKey,
            author_name = it.authorName,
            cover_edition_key = it.coverEditionKey,
            cover_i = it.coverI,
            ebook_access = it.ebookAccess,
            ebook_count_i = it.ebookCountI,
            has_fulltext = it.hasFulltext,
            ia = it.ia,
            ia_collection_s = it.iaCollectionS,
            language = it.language,
            seed = it.seed,
            subject = it.subject,
            subject_facet = it.subjectFacet,
            subject_key = it.subjectKey,
            title = it.title,
            title_suggest = it.titleSuggest,
            type = it.type
        )
    }
}

/**
 * Map [OpenLibraryBook] to database entities
 */
fun List<OpenLibraryBook>.asDatabaseModel(): List<BooksEntity> {
    return map {
        BooksEntity(
            key = it.key,
            version = it._version_,
            authorAlternativeName = it.author_alternative_name,
            authorFacet = it.author_facet,
            authorKey = it.author_key,
            authorName = it.author_name,
            coverEditionKey = it.cover_edition_key,
            coverI = it.cover_i,
            ebookAccess = it.ebook_access,
            ebookCountI = it.ebook_count_i,
            hasFulltext = it.has_fulltext,
            ia = it.ia,
            iaCollectionS = it.ia_collection_s,
            language = it.language,
            seed = it.seed,
            subject = it.subject,
            subjectFacet = it.subject_facet,
            subjectKey = it.subject_key,
            title = it.title,
            titleSuggest = it.title_suggest,
            type = it.type
        )
    }
}