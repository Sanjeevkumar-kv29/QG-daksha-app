package com.quickghy.qgdaksha.data.auth.repositories

import android.util.Log
import com.quickghy.qgdaksha.data.PrefDataStore
import com.quickghy.qgdaksha.data.auth.network.AuthMainApis
import com.quickghy.qgdaksha.data.auth.network.Response.*
import com.quickghy.qgdaksha.data.auth.network.request.UserData
import com.quickghy.qgdaksha.data.auth.network.request.loginViaOtp
import com.quickghy.qgdaksha.data.auth.network.request.loginWithPass
import com.quickghy.qgdaksha.data.auth.network.request.signupRequest
import com.quickghy.qgdaksha.data.dash.profile.network.Request.NotificationSettings
import com.quickghy.qgdaksha.data.dash.profile.network.Request.SettingRequestBody
import com.quickghy.qgdaksha.data.dash.profile.network.Request.Settings
import com.quickghy.qgdaksha.data.dash.profile.network.SettingsApis
import retrofit2.Response

/**
 * @Author: Sanjeev Kumar
 * @Date: 21-06-2021
 */

class AuthUserRepository(

    val APIFORSETTING:SettingsApis,
    val APICALL:AuthMainApis,
    val dataStore: PrefDataStore
) {

    suspend fun saveDATAtoDS(uid: String, uname: String,uphone: String,email: String,isadmin:String,isserviceprovider: String,utoken: String)
    {
        dataStore.savedetailstoDS(uid,uname,uphone,email,isadmin,isserviceprovider,utoken)
    }

    suspend fun GoogleAuth(token: String): String {
        var resp = APICALL.googleAuth(token)
        Log.d("GToken", dataStore.Token.toString())
        if (resp.isSuccessful){
            Log.d("trueresp",resp.body()?.token.toString())
            saveDATAtoDS("null","null","null","null","null","null", resp.body()?.token.toString())
            return "true"
        }
        else{
            Log.d("trueresp", resp.body()?.message.toString())
            return resp.code().toString()
        }

    }

    suspend fun userLoginWithPass(
        mobile: String,
        password: String): String{
        val resp =  APICALL.userLoginWithPass(loginWithPass(mobile,password))
        if (resp.isSuccessful){
            val token = resp.body()?.token
            saveDATAtoDS("null","null","null","null","null","null", resp.body()?.token.toString())
            Log.d("login resp", token.toString())
            return "true"
        }
        else{
            return resp.code().toString()
        }
    }

    suspend fun SendSignupOtp( mobile: String ): String {
        val resp =  APICALL.SendLoginOtp(mobile)
        Log.d("req OTP","requesting for otp  reposetory")
        if (resp.isSuccessful){
            Log.d("req OTP", resp.body()?.message.toString())
            return "true"
        }
        else{
            return  resp.message().toString()
        }

    }

    suspend fun verifyOtpandLogin(phone:String,otp: String ): String {

        Log.d("repo Phone",phone)

        val respon = APICALL.verifySignupOTPandLogin(loginViaOtp(phone,otp))

        if (respon.isSuccessful){

            saveDATAtoDS("null","null","null","null","null","null", respon.body()?.token.toString())
            return "true"
            Log.d("req OTP","recived for otp")

        }
        else{
            return  respon.message().toString()
        }
    }

    suspend fun userSignUp(userData: UserData?, otp: String,): String {

        val resp = APICALL.userSignUp(signupRequest(userData!!,otp))
        if (resp.isSuccessful){
            Log.d("req OTP", resp.body()?.message.toString())
            saveDATAtoDS("null","null","null","null","null","null", resp.body()?.token.toString())

            return "true"
        }
        else{
            Log.d("req OTP", resp.body()?.message.toString())
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


    suspend fun settingSET( ){
        val resp =  APIFORSETTING.SetSettings(SettingRequestBody(Settings(NotificationSettings(true, true, true, true))))

        if (resp.isSuccessful){
            Log.d("req OTP", resp.body().toString())
            }
        else{

        }

    }








}