package com.quickghy.qgdaksha.data.auth.repositories

import android.util.Log
import com.quickghy.qgdaksha.data.PrefDataStore
import com.quickghy.qgdaksha.data.auth.network.AuthMainApis
import com.quickghy.qgdaksha.data.auth.network.Response.*
import com.quickghy.qgdaksha.data.auth.network.request.UserData
import com.quickghy.qgdaksha.data.auth.network.request.loginViaOtp
import com.quickghy.qgdaksha.data.auth.network.request.loginWithPass
import com.quickghy.qgdaksha.data.auth.network.request.signupRequest
import retrofit2.Response

/**
 * @Author: Sanjeev Kumar
 * @Date: 21-06-2021
 */

class AuthUserRepository(

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







}