package com.example.arti.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import okio.IOException


/*private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = Constants.LAYOUT_PREFERENCES_NAME
)*/

class LocalDataSource(private val dataStore: DataStore<Preferences>) {

    private val IS_LINEAR_LAYOUT_MANAGER = booleanPreferencesKey("is_linear_layout_manager")

    val layoutTypeStream: Flow<Boolean> = dataStore.data
        .catch {
            if (it is IOException) {
                it.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            // On the first run of the app, we will use LinearLayoutManager by default
            preferences[IS_LINEAR_LAYOUT_MANAGER] ?: true
        }

    suspend fun saveLayoutToPreferencesStore(isLinearLayoutManager: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_LINEAR_LAYOUT_MANAGER] = isLinearLayoutManager
        }
    }
}