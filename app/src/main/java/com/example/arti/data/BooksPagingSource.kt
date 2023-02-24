package com.example.arti.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.arti.data.model.OpenLibraryBook
import com.example.arti.data.network.BooksApiService
import javax.inject.Inject

const val STARTING_KEY = 0

class BooksPagingSource @Inject constructor(
    private val apiService: BooksApiService,
    private val query: String
) : PagingSource<Int, OpenLibraryBook>() {
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, OpenLibraryBook> {
        try {
            // Start refresh at page 1 if undefined.
            val nextPageNumber = params.key ?: STARTING_KEY


            val response = apiService.searchUsers(query, nextPageNumber)
            return LoadResult.Page(
                data = response.users,
                prevKey = null, // Only paging forward.
                nextKey = response.nextPageNumber
            )


        } catch (e: Exception) {
            // Handle errors in this block and return LoadResult.Error if it is an
            // expected error (such as a network failure).
        }
    }

    override fun getRefreshKey(state: PagingState<Int, OpenLibraryBook>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}