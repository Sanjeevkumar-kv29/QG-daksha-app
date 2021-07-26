package com.quickghy.qgdaksha.data.auth.repositories

import com.quickghy.qgdaksha.data.PrefDataStore
import com.quickghy.qgdaksha.data.auth.network.MainApis
import com.quickghy.qgdaksha.data.auth.network.Response.*
import retrofit2.Response

/**
 * @Author: Sanjeev Kumar
 * @Date: 21-06-2021
 */

class AuthUserRepository(

    val APICALL:MainApis,
    val dataStore: PrefDataStore
) {


    suspend fun saveDATAtoDS(uid: String, uname: String, utoken: String, uphone: String )
    {
        dataStore.savedetailstoDS(uid,uname,utoken,uphone)
    }

    suspend fun userLogin(
        mobile: String,
        password: String,
        daksha_key: String
    ): Response<AuthLoginResponse> {
        return APICALL.userLogin(mobile, password, daksha_key)
    }

    suspend fun userForgetPass(
        mobile: String,
        daksha_key: String
    ): Response<AuthForgetPasswordResponse> {
        return APICALL.userForgetPass(mobile, daksha_key)
    }

    suspend fun userResetPass(
        mobile: String,
        otp: String,
        password: String,
        daksha_key: String
    ): Response<AuthPasswordResetRespones> {
        return APICALL.userResetPass(mobile, otp, password, daksha_key)
    }


    suspend fun userSignUp(
        name: String,
        mobile: String,
        password: String,
        daksha_key: String
    ): Response<AuthSignUpResponse> {
        return APICALL.userSignUp(name, mobile, password, daksha_key)
    }

    suspend fun userSignUpOtp(
        mobile: String,
        otp: String,
        daksha_key: String
    ): Response<AuthSignUpOtpResponse> {
        return APICALL.userSignUpOtp(mobile, otp, daksha_key)
    }

}