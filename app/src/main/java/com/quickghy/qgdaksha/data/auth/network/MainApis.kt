package com.quickghy.qgdaksha.data.auth.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * @Author: Sanjeev Kumar
 * @Date: 21-06-2021
 */

interface MainApis {
    @FormUrlEncoded
    @POST("Dgu_Mob/mob_dguLoginAttempt")
    fun userLogin(
        @Field("ru_phone") email: String,
        @Field("ru_password") password:String,
        @Field("daksha_key") key:String,
    ): Call<ResponseBody>

    companion object{

        operator  fun invoke() :MainApis{
            return Retrofit.Builder()
                .baseUrl("https://dakshassam.org/daksha_app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MainApis::class.java)
        }
    }

}