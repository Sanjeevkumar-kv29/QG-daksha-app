package com.quickghy.qgdaksha.data.auth.network.Response

/**
 * @Author: Shubham Rimjha
 * @Date: 27-06-2021
 */

data class AuthForgetPasswordResponse(
    val opt: String?
)

data class AuthLoginResponse(
    val opt: String?,
    val user_id: String?,
    val user_name: String?,
    val access_token: String?
)

data class AuthSignUpOtpResponse(
    val opt: String?,
    val access_token: String?
)

data class AuthSignUpResponse(
    val opt: String?
)

data class AuthPasswordResetRespones(
    val opt: String?
)