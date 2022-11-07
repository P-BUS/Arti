package com.example.arti.data.repository

import com.example.arti.data.datastore.LocalDataSource
import kotlinx.coroutines.flow.Flow


class LayoutRepository(private val localDataSource: LocalDataSource) {

    // Read
    val layoutTypeStream: Flow<Boolean> = localDataSource.layoutTypeStream

    // Write
    suspend fun saveLayoutToPreferencesStore(isLinearLayoutManager: Boolean) =
        localDataSource.saveLayoutToPreferencesStore(isLinearLayoutManager)
}