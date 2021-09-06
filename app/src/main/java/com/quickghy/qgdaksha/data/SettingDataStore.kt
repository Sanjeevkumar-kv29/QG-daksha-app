package com.quickghy.qgdaksha.data

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * @Author: Shubham Rimjha
 * @Date: 06-09-2021
 */
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "UserSettingsDatastore")

class SettingDataStore(context: Context) {

    private val appContext = context.applicationContext

    suspend fun saveSettingsToDS(offer: Boolean, push: Boolean, text: Boolean, mail: Boolean) {
        appContext.dataStore.edit { preferences ->
            preferences[SettingOffer] = offer
            preferences[SettingPush] = push
            preferences[SettingText] = text
            preferences[SettingMail] = mail
        }

        Log.i(
            "SettingsDS", "saveSettingsToDS: Settings updated: \n" +
                    "preferences[SettingOffer] = $offer\n" +
                    "preferences[SettingPush] = $push\n" +
                    "preferences[SettingText] = $text\n" +
                    "preferences[SettingNotif] = $mail"
        )
    }

    val offerSetting: Flow<Boolean?>
        get() = appContext.dataStore.data.map { preferences ->
            preferences[SettingOffer]
        }
    val pushSetting: Flow<Boolean?>
        get() = appContext.dataStore.data.map { preferences ->
            preferences[SettingPush]
        }
    val textSetting: Flow<Boolean?>
        get() = appContext.dataStore.data.map { preferences ->
            preferences[SettingText]
        }
    val mailSetting: Flow<Boolean?>
        get() = appContext.dataStore.data.map { preferences ->
            preferences[SettingMail]
        }

    suspend fun clear() {
        appContext.dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {
        private val SettingOffer = booleanPreferencesKey("offer")
        private val SettingPush = booleanPreferencesKey("push")
        private val SettingText = booleanPreferencesKey("text")
        private val SettingMail = booleanPreferencesKey("mail")
    }
}