package com.example.arti.data.repository

import com.example.arti.data.datastore.BooksDataStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LayoutRepository @Inject constructor(
    private val localDataSource: BooksDataStore
) {

    // Read
    val layoutTypeStream: Flow<Boolean> = localDataSource.layoutTypeStream

    // Write
    suspend fun saveLayoutToPreferencesStore(isLinearLayoutManager: Boolean) =
        localDataSource.saveLayoutToPreferencesStore(isLinearLayoutManager)
}