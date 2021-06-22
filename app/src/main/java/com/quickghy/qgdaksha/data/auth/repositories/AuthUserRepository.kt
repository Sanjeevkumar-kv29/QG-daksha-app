package com.quickghy.qgdaksha.data.auth.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.quickghy.qgdaksha.data.auth.network.MainApis
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @Author: Sanjeev Kumar
 * @Date: 21-06-2021
 */

class AuthUserRepository {

    fun userLogin(mobile: String, password: String,daksha_key: String): LiveData<String> {

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
    }

}