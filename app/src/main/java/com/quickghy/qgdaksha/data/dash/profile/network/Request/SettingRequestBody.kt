package com.quickghy.qgdaksha.data.dash.profile.network.Request

data class SettingRequestBody(
    val settings: Settings
)

data class Settings(
    val notificationSettings: NotificationSettings
)

data class NotificationSettings(
    var emailNotif: Boolean,
    var offerNotif: Boolean,
    var pushNotif: Boolean,
    var textNotif: Boolean
)