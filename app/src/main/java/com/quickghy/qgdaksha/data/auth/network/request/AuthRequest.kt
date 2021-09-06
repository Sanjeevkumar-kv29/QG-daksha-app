package com.quickghy.qgdaksha.data.auth.network.request
/**
 * @Author: Sanjeev Kumar
 * @Date: 27-07-2021
 */
data class signupRequest(

    val userData: UserData,
    val otp: String
)
data class UserData(
    val email: String,
    val name: String,
    val password: String,
    val phoneNo: String
)

data class loginViaOtp(
    val phoneNo: String,
    val otp: String
)

data class loginWithPass(
    val phoneNo: String,
    val password: String
)


data class SendOtp(
    val phoneNo: String
)