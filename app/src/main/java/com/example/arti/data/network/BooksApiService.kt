package com.example.arti.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksApiService {
    //Searches e-books with full text by description and language
    @GET("search.json")
    suspend fun getSearchBooks(
        @Query("q") searchText: String = "Ukraine",
        @Query("language") booksLanguage: String = "ukr",
        @Query("has_fulltext") hasFullText: String = "true",
        @Query("mode") typeOfDocument: String = "ebooks"
    ): Response<OpenLibrarySearchResponse>
}

