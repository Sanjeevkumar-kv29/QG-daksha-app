package com.quickghy.qgdaksha.data.dash.profile.repositories

import android.content.Context
import android.util.Log
import com.quickghy.qgdaksha.data.PrefDataStore
import com.quickghy.qgdaksha.data.auth.network.AuthMainApis
import com.quickghy.qgdaksha.data.auth.network.Response.*
import com.quickghy.qgdaksha.data.auth.network.request.UserData
import com.quickghy.qgdaksha.data.auth.network.request.loginViaOtp
import com.quickghy.qgdaksha.data.auth.network.request.loginWithPass
import com.quickghy.qgdaksha.data.auth.network.request.signupRequest
import com.quickghy.qgdaksha.data.dash.profile.network.ProfileApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception

/**
 * @Author: Sanjeev Kumar
 * @Date: 21-06-2021
 */

class ProfileRepository(

    val context: Context,
    val dataStore: PrefDataStore
) {

    suspend fun isuserlogincheck(): Boolean
    {
        var savedToken = ""
        CoroutineScope(Dispatchers.Main).launch {

            try {
                PrefDataStore(context).Token.collect{ value ->
                    Log.d("datastorePhonenumber",value.toString())

                    savedToken = value.toString()
                }
            } catch (e: Exception) {
                println("The flow has thrown an exception: $e")
            }

        }
        return savedToken.isNullOrEmpty()
    }


}