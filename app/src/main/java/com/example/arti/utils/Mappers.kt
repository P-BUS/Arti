package com.example.arti.utils

import com.example.arti.data.database.BooksEntity
import com.example.arti.data.network.OpenLibraryBook


/**
 * Map [BooksEntity] to domain entities
 */
fun List<BooksEntity>.asDomainModel(): List<OpenLibraryBook> {
    return map {
        OpenLibraryBook(
            key = it.key,
            version = it.version,
            authorAlternativeName = it.authorAlternativeName,
            authorFacet = it.authorFacet,
            authorKey = it.authorKey,
            authorName = it.authorName,
            coverEditionKey = it.coverEditionKey,
            coverI = it.coverI,
            ebookAccess = it.ebookAccess,
            ebookCountI = it.ebookCountI,
            hasFulltext = it.hasFulltext,
            ia = it.ia,
            iaCollectionS = it.iaCollectionS,
            language = it.language,
            seed = it.seed,
            subject = it.subject,
            subjectFacet = it.subjectFacet,
            subjectKey = it.subjectKey,
            title = it.title,
            titleSuggest = it.titleSuggest,
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
            version = it.version,
            authorAlternativeName = it.authorAlternativeName,
            authorFacet = it.authorFacet,
            authorKey = it.authorKey,
            authorName = it.authorName,
            coverEditionKey = it.coverEditionKey,
            coverI = it.coverI,
            ebookAccess = it.ebookAccess,
            ebookCountI = it.ebookCountI,
            hasFulltext = it.hasFulltext,
            ia = it.ia,
            iaCollectionS = it.iaCollectionS,
            language = it.language,
            seed = it.seed,
            subject = it.subject,
            subjectFacet = it.subjectFacet,
            subjectKey = it.subjectKey,
            title = it.title,
            titleSuggest = it.titleSuggest,
            type = it.type
        )
    }
}