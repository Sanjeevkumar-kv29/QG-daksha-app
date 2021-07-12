package com.quickghy.qgdaksha.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.internshala.Usery.database.UserDAO
import com.internshala.booky.database.UserDatabase
import com.internshala.booky.database.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

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
class UserDataRepository constructor(val context: Context, val database: UserDatabase) {

    var userDao: UserDAO = database.userDao()
    var user: UserEntity? = null

    suspend fun getUserfromDB(){
        user = userDao.getUser()
    }

    suspend fun insertUserIntoDB(userEntity: UserEntity){
        userDao.insertUser(userEntity)
    }

    private object user_prefs {
        val U_Name = stringPreferencesKey("u_name")
        val U_Phone = stringPreferencesKey("u_phone")
        val access_token = stringPreferencesKey("u_token")
    }

    private val Context.dataStore by preferencesDataStore("USER")


    suspend fun saveUser(user: UserData) {
        context.dataStore.edit {
            it[user_prefs.U_Name] = user.name
            it[user_prefs.U_Phone] = user.phone
            it[user_prefs.access_token] = user.access_token
        }

    }

    val readAcToken: Flow<String> = context.dataStore.data.catch {
        if (this is Exception) {
            emit(emptyPreferences())
        }
    }.map {
        val a_c = it[user_prefs.access_token] ?: ""
        a_c
    }

    val readUser: Flow<Any> = context.dataStore.data.catch {
        if (this is Exception) {
            emit(emptyPreferences())
        }
    }.map {
       val user = UserData(it[user_prefs.U_Phone] ?: "",it[user_prefs.U_Name] ?: "",it[user_prefs.access_token] ?: "")
        user
    }
}
