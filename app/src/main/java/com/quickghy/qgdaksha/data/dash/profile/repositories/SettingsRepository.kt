package com.quickghy.qgdaksha.data.dash.profile.repositories

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.quickghy.qgdaksha.data.SettingDataStore
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect

/**
 * @Author: Shubham Rimjha
 * @Date: 06-09-2021
 */
class SettingsRepository(
    val context: Context,
) {
    var offer = MutableLiveData<Boolean>()
    var push = MutableLiveData<Boolean>()
    var text = MutableLiveData<Boolean>()
    var mail = MutableLiveData<Boolean>()

    suspend fun getNotifSetFlow(): Boolean {
        coroutineScope {
            SettingDataStore(context).offerSetting.collect { value ->
                offer.postValue(value)
            }
        }
        return true
    }

    suspend fun getPushSetFlow(): Boolean {
        coroutineScope {
            SettingDataStore(context).pushSetting.collect { value ->
                push.postValue(value)
            }
        }
        return true
    }

    suspend fun getTextSetFlow(): Boolean {
        coroutineScope {
            SettingDataStore(context).textSetting.collect { value ->
                text.postValue(value)
            }
        }
        return true
    }

    suspend fun getMailSetFlow(): Boolean {
        coroutineScope {
            SettingDataStore(context).mailSetting.collect { value ->
                mail.postValue(value)
            }
        }
        return true
    }

}