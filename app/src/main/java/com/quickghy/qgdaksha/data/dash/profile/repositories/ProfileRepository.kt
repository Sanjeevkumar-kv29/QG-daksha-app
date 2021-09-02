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
import com.quickghy.qgdaksha.data.dash.home.repositories.HomeRepository
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
    val homerepo: HomeRepository

) {
    val profiletoken = homerepo.token
    val userprofileData = homerepo.userprofileData





}