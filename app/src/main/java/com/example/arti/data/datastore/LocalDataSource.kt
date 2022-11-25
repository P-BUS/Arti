package com.example.arti.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.arti.other.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import okio.IOException
import javax.inject.Inject

// Access dataStore through this property throughout the rest of application
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = Constants.LAYOUT_PREFERENCES_NAME
)

class LocalDataSource @Inject constructor(private val dataStore: DataStore<Preferences>) {

    private object PreferencesKeys {
        val IS_LINEAR_LAYOUT_MANAGER = booleanPreferencesKey("is_linear_layout_manager")
    }

    // Read Flow from a Preferences DataStore
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
            preferences[PreferencesKeys.IS_LINEAR_LAYOUT_MANAGER] ?: true
        }

    // Write to a Preferences DataStore
    suspend fun saveLayoutToPreferencesStore(isLinearLayoutManager: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.IS_LINEAR_LAYOUT_MANAGER] = isLinearLayoutManager
        }
    }
}