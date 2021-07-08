package com.quickghy.qgdaksha.data.auth.repositories

import com.internshala.booky.database.UserEntity
import com.quickghy.qgdaksha.data.auth.network.MainApis
import com.quickghy.qgdaksha.data.auth.network.Response.*
import retrofit2.Response

/**
 * @Author: Sanjeev Kumar
 * @Date: 21-06-2021
 */

class AuthUserRepository {

    /*fun userLogin(mobile: String, password: String,daksha_key: String): LiveData<String> {

        val LoginResponse = MutableLiveData<String>()

        MainApis().userLogin(mobile,password,daksha_key)  //tight cuppeld we just remove is using DI
            .enqueue(object:Callback<ResponseBody>{

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful){

                        LoginResponse.value = response.body()?.string()
                    }
                    else{
                        LoginResponse.value = response.errorBody()?.string()

                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {


                    LoginResponse.value = t.message
                }


            })

        return LoginResponse
    }*/

    suspend fun userLogin(
        mobile: String,
        password: String,
        daksha_key: String
    ): Response<AuthLoginResponse> {
        return MainApis().userLogin(mobile, password, daksha_key)
    }

    suspend fun userForgetPass(
        mobile: String,
        daksha_key: String
    ): Response<AuthForgetPasswordResponse> {
        return MainApis().userForgetPass(mobile, daksha_key)
    }

    suspend fun userResetPass(
        mobile: String,
        otp: String,
        password: String,
        daksha_key: String
    ): Response<AuthPasswordResetRespones> {
        return MainApis().userResetPass(mobile, otp, password, daksha_key)
    }


    suspend fun userSignUp(
        name: String,
        mobile: String,
        password: String,
        daksha_key: String
    ): Response<AuthSignUpResponse> {
        return MainApis().userSignUp(name, mobile, password, daksha_key)
    }

    suspend fun userSignUpOtp(
        mobile: String,
        otp: String,
        daksha_key: String
    ): Response<AuthSignUpOtpResponse> {
        return MainApis().userSignUpOtp(mobile, otp, daksha_key)
    }

}