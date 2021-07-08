package com.quickghy.qgdaksha.util

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

/**
 * @Author: Shubham Rimjha
 * @Date: 25-06-2021
 */
class DataStoreUtil {

    private val USER_PREFERENCES_NAME = "user_data"

    private val Context.dataStore by preferencesDataStore(
        name = USER_PREFERENCES_NAME
    )

}