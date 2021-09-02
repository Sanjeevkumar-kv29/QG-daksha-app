package com.quickghy.qgdaksha.util

import com.quickghy.qgdaksha.data.NetworkConnectionInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {
    companion object {
        private var retrofit: Retrofit? = null
        private val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ): Retrofit {
            if(retrofit == null) {

                val okkHttpclient = OkHttpClient.Builder()
                    .addInterceptor(networkConnectionInterceptor)
                    .addInterceptor(interceptor)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .build()

                retrofit =  Retrofit.Builder()
                    .client(okkHttpclient)
                    .baseUrl("https://qg-staging.herokuapp.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }
    }
}