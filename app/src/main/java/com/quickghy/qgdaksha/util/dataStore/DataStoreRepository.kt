package com.quickghy.qgdaksha.util.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore

/**
 * @Author: Shubham Rimjha
 * @Date: 21-06-2021
 */

const val USER_PREFERENCES_NAME = "login_Preferences"


class DataStoreRepository(
    private val userPreferencesStore: DataStore<LoginPreferences>,
    context: Context
) {

    private val Context.dataStore by preferencesDataStore(
        name = USER_PREFERENCES_NAME
    )

    /*fun saveToLoginDataStore(name: String) {
        dataStore.edit { preference ->
            preference[]
        }
    }*/
}