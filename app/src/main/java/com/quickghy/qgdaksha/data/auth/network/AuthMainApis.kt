package com.quickghy.qgdaksha.data.auth.network

import com.quickghy.qgdaksha.data.auth.network.Response.*
import com.quickghy.qgdaksha.data.auth.network.request.loginViaOtp
import com.quickghy.qgdaksha.data.auth.network.request.loginWithPass
import com.quickghy.qgdaksha.data.auth.network.request.signupRequest
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

/**
 * @Author: Sanjeev Kumar
 * @Date: 21-06-2021
 */

interface AuthMainApis {

    @POST("/api/v1/user/login/password")
    suspend fun userLoginWithPass(
        @Body loginpass:loginWithPass
    ): Response<AuthLoginResponse>


    @FormUrlEncoded
    @POST("/api/v1/user/otp")
    suspend fun SendLoginOtp(
        @Field("phoneNo") phone: String,
    ): Response<SignupOtpResp> // recieve access token on successful sign up.

    @POST("/api/v1/user/login/otp")
    suspend fun verifySignupOTPandLogin(
        @Body loginOtp: loginViaOtp
    ): Response<AuthLoginResponse> // recieve access token on successful sign up.


    @GET("/api/v1/user/google/callback")
    suspend fun googleAuth(
        // suspend function because this may run long
        @Query("token") token: String
    ): Response<GoogleAuthResp> // recieve access token on successful sign up.

    @POST("/api/v1/user/signUp")
    suspend fun userSignUp(
        @Body userData: signupRequest,
    ): Response<AuthSignUpResponse>


    @FormUrlEncoded
    @POST("Dgu_Mob/mob_enterVerCodeForgotPass")
    suspend fun userResetPass(
        // suspend function because this may run long
        @Field("ru_phone") phone: String,
        @Field("ru_v_code") otp: String,
        @Field("ru_password") password: String,
        @Field("daksha_key") key: String,
    ): Response<AuthPasswordResetRespones>  // Response kept on auth login response data class inside response directory
}