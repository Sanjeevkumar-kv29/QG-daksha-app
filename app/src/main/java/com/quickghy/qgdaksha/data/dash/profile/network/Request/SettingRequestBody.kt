package com.quickghy.qgdaksha.data.dash.profile.network.Request

data class SettingRequestBody(
    val settings: Settings
)
data class Settings(
    val notificationSettings: NotificationSettings
)

data class NotificationSettings(
    val emailNotif: Boolean,
    val offerNotif: Boolean,
    val pushNotif: Boolean,
    val textNotif: Boolean
)