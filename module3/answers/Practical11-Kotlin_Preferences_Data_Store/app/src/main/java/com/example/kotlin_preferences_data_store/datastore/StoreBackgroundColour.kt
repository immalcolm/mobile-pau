package com.example.kotlin_preferences_data_store.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreBackgroundColour(private val context: Context) {

    // to make sure there is only one instance
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("BackgroundColour")
        val BACKGROUND_COLOUR_KEY = stringPreferencesKey("background_colour")
        val IS_SWITCH_ON_KEY = booleanPreferencesKey("is_switch_on")
    }

    val getBackgroundColour: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[BACKGROUND_COLOUR_KEY] ?: ""
        }

    val isSwitchOn: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[IS_SWITCH_ON_KEY] ?: false
        }

    suspend fun saveBackgroundColour(colour: String) {
        context.dataStore.edit { preferences ->
            preferences[BACKGROUND_COLOUR_KEY] = colour
        }
    }

    suspend fun setSwitchOn(isOn: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_SWITCH_ON_KEY] = isOn
        }
    }

}