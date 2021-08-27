package com.quickghy.qgdaksha.data.auth.network.Response

/**
 * @Author: Shubham Rimjha
 * @Date: 27-06-2021
 */

data class AuthForgetPasswordResponse(
    val opt: String?
)

data class AuthLoginResponse(
    val _id: String?,
    val name: String?,
    val phoneNo: String?,
    val email: String?,
    val isAdmin: String?,
    val isServiceProvider: String?,
    val token: String?
)

data class AuthSignUpResponse(
    val message: String?,
    val token: String?
)

data class AuthPasswordResetRespones(
    val opt: String?
)

data class GoogleAuthResp(
    val message: String?,
    val token: String?,
    val stack: String?
)

data class SignupOtpResp(
    val message: String?,
    val otp: String?
)
