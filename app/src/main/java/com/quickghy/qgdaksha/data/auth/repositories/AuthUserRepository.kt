package com.quickghy.qgdaksha.data.auth.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.quickghy.qgdaksha.data.auth.network.MainApis
import com.quickghy.qgdaksha.data.auth.network.Response.AuthForgetPasswordResponse
import com.quickghy.qgdaksha.data.auth.network.Response.AuthLoginResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.contracts.Returns

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

    suspend fun userLogin(mobile: String, password: String,daksha_key: String): Response<AuthLoginResponse> {
       return MainApis().userLogin(mobile,password,daksha_key)
    }

    suspend fun userForgetPass(mobile: String,daksha_key: String): Response<AuthForgetPasswordResponse> {
        return MainApis().userForgetPass(mobile,daksha_key)
    }



}