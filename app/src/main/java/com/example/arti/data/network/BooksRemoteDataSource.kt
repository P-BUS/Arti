package com.example.arti.data.network

import javax.inject.Inject


class BooksRemoteDataSource @Inject constructor(
    private val booksApiService: BooksApiService
) {
    suspend fun invoke(): ApiResult<OpenLibrarySearchResponse> =
        handleApiResponse {
            booksApiService.getSearchBooks()
        }
}