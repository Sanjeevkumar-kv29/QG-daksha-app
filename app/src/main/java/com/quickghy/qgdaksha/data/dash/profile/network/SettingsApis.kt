package com.quickghy.qgdaksha.data.dash.profile.network

import com.quickghy.qgdaksha.data.NetworkConnectionInterceptor
import com.quickghy.qgdaksha.data.dash.profile.network.Request.SettingRequestBody
import com.quickghy.qgdaksha.data.dash.profile.network.Response.GetSettingsResponse
import com.quickghy.qgdaksha.data.dash.profile.network.Response.SetSettingsResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

/**
 * @Author: Shubham Rimjha
 * @Date: 04-09-2021
 */
interface SettingsApis {

    @GET("/api/v1/user/settings")
    suspend fun GetSettings(
        @Header("Authorization") token: String
    ): Response<GetSettingsResponse> // recieve settings on successful initialization.

    @POST("/api/v1/user/settings")
    suspend fun SetSettings(
        // suspend function because this may run long
        @Body settingRequest: SettingRequestBody
    ): Response<SetSettingsResponse>


    companion object {
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ): SettingsApis {
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
                .create(SettingsApis::class.java)
        }
    }

}