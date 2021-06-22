package com.quickghy.qgdaksha.data.auth.network

import com.quickghy.qgdaksha.data.auth.network.Response.AuthLoginResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
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
    suspend fun userLogin(                                         // suspend function because this may run long
        @Field("ru_phone") phone: String,
        @Field("ru_password") password:String,
        @Field("daksha_key") key:String,
    ): Response<AuthLoginResponse>    // Response kept on auth login response data class inside response directory

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