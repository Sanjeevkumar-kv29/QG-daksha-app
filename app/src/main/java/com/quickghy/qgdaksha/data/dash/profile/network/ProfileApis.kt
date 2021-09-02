package com.quickghy.qgdaksha.data.dash.profile.network

import android.text.util.Rfc822Token
import com.quickghy.qgdaksha.data.NetworkConnectionInterceptor
import com.quickghy.qgdaksha.data.auth.network.Response.*
import com.quickghy.qgdaksha.data.auth.network.request.loginViaOtp
import com.quickghy.qgdaksha.data.auth.network.request.loginWithPass
import com.quickghy.qgdaksha.data.auth.network.request.signupRequest
import com.quickghy.qgdaksha.data.dash.profile.network.Response.GetProfileResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

/**
 * @Author: Sanjeev Kumar
 * @Date: 21-06-2021
 */

interface ProfileApi{

    @GET("/api/v1/user/profile")
    suspend fun GetProfile(
        @Header("Authorization") token: String
    ): Response<GetProfileResponse> // recieve access token on successful sign up.




    companion object {

        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ): ProfileApi {
             val interceptor = run {
                val httpLoggingInterceptor = HttpLoggingInterceptor()
                httpLoggingInterceptor.apply {
                    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                    print(httpLoggingInterceptor)
                }
            }

             val okkHttpclient1 = OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor) // same for .addInterceptor(...)
                .connectTimeout(30, TimeUnit.SECONDS) //Backend is really slow
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()

            val okkHttpclient2 = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okkHttpclient1)
                .client(okkHttpclient2)
                .baseUrl("https://qg-staging.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ProfileApi::class.java)
        }
    }
}