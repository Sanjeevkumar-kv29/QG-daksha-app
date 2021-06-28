package com.quickghy.qgdaksha.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

/**
 * @Author: Shubham Rimjha
 * @Date: 23-06-2021
 */
data class UserData(
    val phone: String,
    val name: String,
    val access_token: String,
)

//this repo holds the access token and user details such as user name phone and others.
class UserDataRepository(
    context: Context
) {

    private val applicationContext = context.applicationContext
    private val Context.dataStore by preferencesDataStore("USER")

    private object user_prefs {
        val U_Name = stringPreferencesKey("u_name")
        val U_Phone = stringPreferencesKey("u_phone")
        val access_token = stringPreferencesKey("u_token")
    }

    suspend fun saveUser(user: UserData) {
        applicationContext.dataStore.edit { user_prefs ->
            user_prefs[UserDataRepository.user_prefs.U_Name] = user.name
            user_prefs[UserDataRepository.user_prefs.U_Phone] = user.phone
            user_prefs[UserDataRepository.user_prefs.access_token] = user.access_token
        }
    }


}
