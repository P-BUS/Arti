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

    //@ColumnInfo(name = "contributor")
    //val contributor: List<String>,

    @ColumnInfo(name = "cover_edition_key")
    val coverEditionKey: String,

    @ColumnInfo(name = "cover_i")
    val coverI: Int,

    //@ColumnInfo(name = "ddc")
    //val ddc: List<String>,

    //@ColumnInfo(name = "ddc_sort")
    //val ddcSort: String,

    @ColumnInfo(name = "ebook_access")
    val ebookAccess: String,

    @ColumnInfo(name = "ebook_count_i")
    val ebookCountI: Int,

    @ColumnInfo(name = "edition_count")
    val editionCount: Int,

    @ColumnInfo(name = "edition_key")
    val editionKey: List<String>,

    @ColumnInfo(name = "first_publish_year")
    val firstPublishYear: Int,

    @ColumnInfo(name = "has_fulltext")
    val hasFulltext: Boolean,

    @ColumnInfo(name = "ia")
    val ia: List<String>,

    @ColumnInfo(name = "ia_collection_s")
    val iaCollectionS: String,

    //@ColumnInfo(name = "id_goodreads")
    //val idGoodreads: List<String>,

    //@ColumnInfo(name = "id_librarything")
    //val idLibrarything: List<String>,

    //@ColumnInfo(name = "isbn")
    //val isbn: List<String>,

    @ColumnInfo(name = "language")
    val language: List<String>,

    @ColumnInfo(name = "last_modified_i")
    val lastModifiedI: Int,

    //@ColumnInfo(name = "lcc")
    //val lcc: List<String>,

    //@ColumnInfo(name = "lcc_sort")
    //val lccSort: String,

    //@ColumnInfo(name = "lccn")
    //val lccn: List<String>,

    //@ColumnInfo(name = "lending_edition_s")
    //val lendingEditionS: String,

    //@ColumnInfo(name = "lending_identifier_s")
    //val lendingIdentifierS: String,

    //@ColumnInfo(name = "number_of_pages_median")
    //val numberOfPagesMedian: Int,

    //@ColumnInfo(name = "oclc")
    //val oclc: List<String>,

    //@ColumnInfo(name = "person")
    //val person: List<String>,

    //@ColumnInfo(name = "person_facet")
    //val personFacet: List<String>,

   // @ColumnInfo(name = "person_key")
    //val personKey: List<String>,

    //@ColumnInfo(name = "place")
    //val place: List<String>,

    //@ColumnInfo(name = "place_facet")
    //val placeFacet: List<String>,

    //@ColumnInfo(name = "place_key")
    //val placeKey: List<String>,

    //@ColumnInfo(name = "printdisabled_s")
    //val printdisabledS: String,

    @ColumnInfo(name = "public_scan_b")
    val publicScanB: Boolean,

    @ColumnInfo(name = "publish_date")
    val publishDate: List<String>,

    //@ColumnInfo(name = "publish_place")
    //val publishPlace: List<String>,

    @ColumnInfo(name = "publish_year")
    val publishYear: List<Int>,

    @ColumnInfo(name = "publisher")
    val publisher: List<String>,

    @ColumnInfo(name = "publisher_facet")
    val publisherFacet: List<String>,

    @ColumnInfo(name = "seed")
    val seed: List<String>,

    @ColumnInfo(name = "subject")
    val subject: List<String>,

    @ColumnInfo(name = "subject_facet")
    val subjectFacet: List<String>,

    @ColumnInfo(name = "subject_key")
    val subjectKey: List<String>,

    //@ColumnInfo(name = "subtitle")
    //val subtitle: String,

    //@ColumnInfo(name = "time")
    //val time: List<String>,

    //@ColumnInfo(name = "time_facet")
    //val timeFacet: List<String>,

    //@ColumnInfo(name = "time_key")
    //val timeKey: List<String>,

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
            //contributor = it.contributor,
            cover_edition_key = it.coverEditionKey,
            cover_i = it.coverI,
            //ddc = it.ddc,
            //ddc_sort = it.ddcSort,
            ebook_access = it.ebookAccess,
            ebook_count_i = it.ebookCountI,
            edition_count = it.editionCount,
            edition_key = it.editionKey,
            first_publish_year = it.firstPublishYear,
            has_fulltext = it.hasFulltext,
            ia = it.ia,
            ia_collection_s = it.iaCollectionS,
            //id_goodreads = it.idGoodreads,
            //id_librarything = it.idLibrarything,
            //isbn = it.isbn,
            language = it.language,
            last_modified_i = it.lastModifiedI,
            //lcc = it.lcc,
            //lcc_sort = it.lccSort,
            //lccn = it.lccn,
            //lending_edition_s = it.lendingEditionS,
            //lending_identifier_s = it.lendingIdentifierS,
            //number_of_pages_median = it.numberOfPagesMedian,
            //oclc = it.oclc,
            //person = it.person,
            //person_facet = it.personFacet,
            //person_key = it.personKey,
            //place = it.place,
            //place_facet = it.placeFacet,
            //place_key = it.placeKey,
            //printdisabled_s = it.printdisabledS,
            public_scan_b = it.publicScanB,
            publish_date = it.publishDate,
            //publish_place = it.publishPlace,
            publish_year = it.publishYear,
            publisher = it.publisher,
            publisher_facet = it.publisherFacet,
            seed = it.seed,
            subject = it.subject,
            subject_facet = it.subjectFacet,
            subject_key = it.subjectKey,
            //subtitle = it.subtitle,
            //time = it.time,
            //time_facet = it.timeFacet,
            //time_key = it.timeKey,
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
            //contributor = it.contributor,
            coverEditionKey = it.cover_edition_key,
            coverI = it.cover_i,
            //ddc = it.ddc,
            //ddcSort = it.ddc_sort,
            ebookAccess = it.ebook_access,
            ebookCountI = it.ebook_count_i,
            editionCount = it.edition_count,
            editionKey = it.edition_key,
            firstPublishYear = it.first_publish_year,
            hasFulltext = it.has_fulltext,
            ia = it.ia,
            iaCollectionS = it.ia_collection_s,
            //idGoodreads = it.id_goodreads,
            //idLibrarything = it.id_librarything,
            //isbn = it.isbn,
            language = it.language,
            lastModifiedI = it.last_modified_i,
            //lcc = it.lcc,
            //lccSort = it.lcc_sort,
            //lccn = it.lccn,
            //lendingEditionS = it.lending_edition_s,
            //lendingIdentifierS = it.lending_identifier_s,
            //numberOfPagesMedian = it.number_of_pages_median,
            //oclc = it.oclc,
            //person = it.person,
            //personFacet = it.person_facet,
            //personKey = it.person_key,
            //place = it.place,
            //placeFacet = it.place_facet,
            //placeKey = it.place_key,
            //printdisabledS = it.printdisabled_s,
            publicScanB = it.public_scan_b,
            publishDate = it.publish_date,
            //publishPlace = it.publish_place,
            publishYear = it.publish_year,
            publisher = it.publisher,
            publisherFacet = it.publisher_facet,
            seed = it.seed,
            subject = it.subject,
            subjectFacet = it.subject_facet,
            subjectKey = it.subject_key,
            //subtitle = it.subtitle,
            //time = it.time,
            //timeFacet = it.time_facet,
            //timeKey = it.time_key,
            title = it.title,
            titleSuggest = it.title_suggest,
            type = it.type
        )
    }
}