package com.example.arti.utils

import com.example.arti.data.database.BooksEntity
import com.example.arti.data.model.OpenLibraryBook


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