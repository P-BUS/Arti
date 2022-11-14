package com.example.arti.data.repository

import com.example.arti.data.datastore.LocalDataSource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LayoutRepository @Inject constructor(
    private val localDataSource: LocalDataSource
) {

    // Read
    val layoutTypeStream: Flow<Boolean> = localDataSource.layoutTypeStream

    // Write
    suspend fun saveLayoutToPreferencesStore(isLinearLayoutManager: Boolean) =
        localDataSource.saveLayoutToPreferencesStore(isLinearLayoutManager)
}