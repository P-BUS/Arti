package com.example.arti.data.repository

import android.util.Log
import com.example.arti.data.database.AppDatabase
import com.example.arti.data.model.OpenLibraryBook
import com.example.arti.data.network.BooksApiService
import com.example.arti.utils.asDatabaseModel
import com.example.arti.utils.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

const val TAG = "BooksRepository"

@Singleton
class BooksRepository @Inject constructor(
    private val database: AppDatabase,
    private val network: BooksApiService
) {

    //Transforms database entity to domain
    val books: Flow<List<OpenLibraryBook>> =
        database.booksDao().getAllBooks()
            .map { it.asDomainModel() }

    /**
     * This method retrieve data from network and refresh the offline database.
     */
    suspend fun refreshBooks(searchText: String) {
        withContext(Dispatchers.IO) {
            // TODO: implement safe response with exceptions handling
            val searchResult = try {
                network.getSearchBooks(
                    searchText = searchText
                )
            } catch (exception: HttpException) {
                Log.e(TAG, "HttpException $exception")
            } catch (networkError: IOException) {
                Log.e(TAG, "IO Exception $networkError, you might not have internet connection")
            } catch (exception: Throwable) {
                Log.e(TAG, "Exception $exception")
            }

            if (searchResult.docs.isNotEmpty()) {
                deleteAllBooks()
                val listBooks: List<OpenLibraryBook> = searchResult.docs
                database.booksDao().insertAll(listBooks.asDatabaseModel())
            }


        }
    }

    suspend fun deleteAllBooks() {
        database.booksDao().deleteAllBooks()
    }
}
