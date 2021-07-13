package com.quickghy.qgdaksha.data.auth.network

import com.quickghy.qgdaksha.data.auth.network.Response.*
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

    // post reqs for login
    @FormUrlEncoded
    @POST("Dgu_Mob/mob_dguLoginAttempt")
    suspend fun userLogin(
        // suspend function because this may run long
        @Field("ru_phone") phone: String,
        @Field("ru_password") password: String,
        @Field("daksha_key") key: String,
    ): Response<AuthLoginResponse>    // Response kept on auth login response data class inside response directory

    //    post reqs for forgot password--------------------------------
    @FormUrlEncoded
    @POST("Dgu_Mob/mob_sendVerCodeForgotPasswordDgu")
    suspend fun userForgetPass(
        // suspend function because this may run long
        @Field("ru_phone") phone: String,
        @Field("daksha_key") key: String,
    ): Response<AuthForgetPasswordResponse>  // Response kept on auth login response data class inside response directory

    @FormUrlEncoded
    @POST("Dgu_Mob/mob_enterVerCodeForgotPass")
    suspend fun userResetPass(
        // suspend function because this may run long
        @Field("ru_phone") phone: String,
        @Field("ru_v_code") otp: String,
        @Field("ru_password") password: String,
        @Field("daksha_key") key: String,
    ): Response<AuthPasswordResetRespones>  // Response kept on auth login response data class inside response directory

    //    post requests for sign up seq------------------------------------
    @FormUrlEncoded
    @POST("Dgu_Mob/mob_registerUser")
    suspend fun userSignUp(
        // suspend function because this may run long
        @Field("ru_name") name: String,
        @Field("ru_phone") phone: String,
        @Field("ru_password") password: String,
        @Field("daksha_key") key: String,
    ): Response<AuthSignUpResponse>

    @FormUrlEncoded
    @POST("Dgu_Mob/mob_enterVerificationCode")
    suspend fun userSignUpOtp(
        // suspend function because this may run long
        @Field("ru_phone") phone: String,
        @Field("ru_v_code") otp: String,
        @Field("daksha_key") key: String,
    ): Response<AuthSignUpOtpResponse> // recieve access token on successful sign up.

    companion object {

        operator fun invoke(): MainApis {
            return Retrofit.Builder()
                .baseUrl("https://dakshassam.org/daksha_app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MainApis::class.java)
        }
    }


}