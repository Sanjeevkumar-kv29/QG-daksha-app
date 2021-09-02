package com.quickghy.qgdaksha.data.dash.profile.network.Response

data class GetProfileResponse(
    val `data`: Data
)

data class Data(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val email: String,
    val isAdmin: Boolean,
    val isServiceProvider: Boolean,
    val name: String,
    val phoneNo: String,
    val updatedAt: String
)