package com.quickghy.qgdaksha.data.auth.repositories

/**
 * @Author: Shubham Rimjha
 * @Date: 23-06-2021
 */
//this repo holds the access token and user details such as user name phone and others.
class UserDataRepository {
    data class UserData(
        val access_token: String,
        val phone: String,
        val name: String,
    )

}