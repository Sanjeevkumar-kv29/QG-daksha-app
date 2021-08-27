package com.quickghy.qgdaksha.data.map.network

import com.quickghy.qgdaksha.data.map.network.response.DirectionResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

/**
 * @Author: Shubham Rimjha
 * @Date: 21-08-2021
 */

interface DirectionsApi {

    @GET("/maps/api/directions/json")
    suspend fun getRoute(
        @Query("origin") origin: String?,
        @Query("destination") destination: String?,
        @Query("mode") mode: String?,
        @Query("key") key: String?,
    ): Response<DirectionResponse>


    companion object {

        operator fun invoke(): DirectionsApi {
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

            return Retrofit.Builder()
                .client(okkHttpclient1)
                .baseUrl("https://maps.googleapis.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DirectionsApi::class.java)
        }
    }
}