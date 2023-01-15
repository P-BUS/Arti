package com.example.arti.data.network

import com.example.arti.data.model.OpenLibrarySearchResponse
import javax.inject.Inject


class BooksRemoteDataSource @Inject constructor(
    private val booksApiService: BooksApiService
) {
    suspend fun invoke(): ApiResult<OpenLibrarySearchResponse> =
        handleApiResponse {
            booksApiService.getSearchBooks()
        }
}