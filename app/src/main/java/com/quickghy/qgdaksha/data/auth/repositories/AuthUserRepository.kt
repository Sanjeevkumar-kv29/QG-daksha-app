package com.quickghy.qgdaksha.data.auth.repositories

import android.util.Log
import com.quickghy.qgdaksha.data.PrefDataStore
import com.quickghy.qgdaksha.data.auth.network.AuthMainApis
import com.quickghy.qgdaksha.data.auth.network.Response.*
import retrofit2.Response

/**
 * @Author: Sanjeev Kumar
 * @Date: 21-06-2021
 */

class AuthUserRepository(

    val APICALL:AuthMainApis,
    val dataStore: PrefDataStore
) {


    suspend fun saveDATAtoDS(uid: String, uname: String, utoken: String, uphone: String )
    {
        dataStore.savedetailstoDS(uid,uname,utoken,uphone)
    }

    suspend fun userLoginWithPass(
        mobile: String,
        password: String): String{
        val resp =  APICALL.userLoginWithPass(mobile, password)
        if (resp.isSuccessful){

            val id = resp.body()?._id
            val name = resp.body()?.name
            val phone = resp.body()?.phoneNo
            val email = resp.body()?.email
            val token = resp.body()?.token

            Log.d("login resp",id+name+email+phone)
            return "true"
        }
        else{
            return resp.code().toString()
        }
    }

    suspend fun verifyOtpandLogin( mobile: String, otp: String ): String {
        val resp = APICALL.verifySignupOTPandLogin(mobile,otp)

        if (resp.isSuccessful){
            return "true"
            Log.d("req OTP","recived for otp")
            Log.d("otp vreify", resp.body()?.token!!)

            // resp carry the login data

        }
        else{
            return  resp.message().toString()
        }
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

    suspend fun SendSignupOtp( mobile: String ): String {
        val resp =  APICALL.sendSignupOTP(mobile)
        Log.d("req OTP","requesting for otp  reposetory")
        if (resp.isSuccessful){
            return "true"
            Log.d("req OTP","recived for otp")
        }
        else{
            return  resp.message().toString()
        }

    }


    suspend fun GoogleAuth(token: String): String {
        var resp = APICALL.googleAuth(token)
        Log.d("GToken", token.toString())
        if (resp.isSuccessful){

            Log.d("true resp","true")
            return "true"
            // save the response into db

        }
        else{
            Log.d("trueresp", resp.body()?.message.toString())

            return resp.code().toString()

        }
    }
}