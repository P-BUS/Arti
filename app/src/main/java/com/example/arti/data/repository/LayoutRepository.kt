package com.example.arti.data.repository

import com.example.arti.data.datastore.LocalDataSource
import kotlinx.coroutines.flow.Flow


class LayoutRepository(private val localDataSource: LocalDataSource) {

    val layoutTypeStream: Flow<Boolean> = localDataSource.layoutTypeStream

    suspend fun saveLayoutToPreferencesStore(isLinearLayoutManager: Boolean) =
        localDataSource.saveLayoutToPreferencesStore(isLinearLayoutManager)
}