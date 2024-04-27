package com.ahmadshubita.moviesapp.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MainDataStore @Inject constructor(@ApplicationContext context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "MainAppDS")
    private val dataStore = context.dataStore


    suspend fun setDarkThemePrefs(isDarkTheme: Boolean) {
        dataStore.edit { pref ->
            pref[DARK_MODE_KEY] = isDarkTheme
        }
    }

    fun getDarkThemePrefs(): Flow<Boolean> =
        dataStore.data.distinctUntilChanged().catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { pref ->
                val onboard = pref[DARK_MODE_KEY] ?: false
                onboard
            }

    companion object {
        val DARK_MODE_KEY = booleanPreferencesKey("DARK_MODE")
    }
}