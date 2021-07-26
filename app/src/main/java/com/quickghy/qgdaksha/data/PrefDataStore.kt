package com.quickghy.qgdaksha.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "UserDetailsDatastore")

class PrefDataStore(context: Context) {

    private val appContext = context.applicationContext

    //to retrive the values we user FLOW that should be nullable

    val Token: Flow<String?>
        get() = appContext.dataStore.data.map { preferences ->
            preferences[DStoken]
        }

    val username: Flow<String?>
        get() = appContext.dataStore.data.map { preferences ->
            preferences[DSusername]
        }
    val uid: Flow<String?>
        get() = appContext.dataStore.data.map { preferences ->
            preferences[DSuid]
        }

    val uphoneno : Flow<String?>
        get() = appContext.dataStore.data.map { preferences ->
            preferences[DSphone]
        }

    suspend fun savedetailstoDS(uid: String, uname: String,utoken: String,uphone: String) {
        appContext.dataStore.edit { preferences ->
            preferences[DSuid] = uid
            preferences[DSusername] = uname
            preferences[DStoken] = utoken
            preferences[DSphone] = uphone
        }
    }


    suspend fun clear() {
        appContext.dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {
        private val DSusername = stringPreferencesKey("user_name")
        private val DSuid = stringPreferencesKey("user_uid")
        private val DStoken = stringPreferencesKey("user_token")
        private val DSphone = stringPreferencesKey("user_phone")
    }

}