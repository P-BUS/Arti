package com.example.arti.data.network

import OpenLibrarySearchResponse
import com.example.arti.other.Constants.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// Build a Retrofit object with the Moshi converter
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface BooksApiService {
    // Searches e-books with full text by description and language
    @GET("search.json")
    suspend fun getSearchBooks(
        @Query("q") searchText: String,
        @Query("language") booksLanguage: String,
        @Query("has_fulltext") hasFullText: Boolean,
        @Query("mode") typeOfDocument: String
    ): Response<OpenLibrarySearchResponse>
}

object BooksApi {
    val retrofitApiService : BooksApiService by lazy {
        retrofit.create(BooksApiService::class.java)
    }
}
