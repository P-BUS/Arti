package com.example.arti.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.arti.Utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Provides
    @Singleton
    // TODO: not sure how to finish it
    fun provideDataStore(context: Context): DataStore<Preferences> {
        // Access dataStore through this property throughout the rest of application
        return val context.dataStore: DataStore<Preferences> by preferencesDataStore(
            name = Constants.LAYOUT_PREFERENCES_NAME
        )
    }
}