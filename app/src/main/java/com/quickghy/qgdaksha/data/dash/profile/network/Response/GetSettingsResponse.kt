package com.quickghy.qgdaksha.data.dash.profile.network.Response

data class GetSettingsResponse(
    val __v: Int,
    val _id: String,
    val notificationSettings: NotificationSettings,
    val userId: String
)

data class NotificationSettings(
    val emailNotif: Boolean,
    val offerNotif: Boolean,
    val pushNotif: Boolean,
    val textNotif: Boolean
)

data class SetSettingsResponse(
    val mssg: String
)