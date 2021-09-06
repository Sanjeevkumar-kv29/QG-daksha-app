package com.quickghy.qgdaksha.ui.dash.profile.settings

import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModel
import com.quickghy.qgdaksha.data.dash.profile.network.Request.NotificationSettings
import com.quickghy.qgdaksha.data.dash.profile.repositories.SettingsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingsViewModel(
    val context: Context,
    val settingRepo: SettingsRepository
) : ViewModel(
) {

    lateinit var settings: NotificationSettings
    lateinit var tempsettings: NotificationSettings

    var offer = settingRepo.offer
    var push = settingRepo.push
    var textNotif = settingRepo.text
    var mail = settingRepo.mail


    fun initializeSettings() {
        CoroutineScope(Dispatchers.IO).launch {
            settingRepo.getNotifSetFlow()
            settingRepo.getPushSetFlow()
            settingRepo.getTextSetFlow()
            settingRepo.getMailSetFlow()
        }
    }

    fun switchOfferNotif(view: View) {
        settings.offerNotif = settings.offerNotif.not()
    }

    fun switchPushNotif(view: View) {
        settings.pushNotif = settings.pushNotif.not()
    }

    fun switchTextNotif(view: View) {
        settings.textNotif = settings.textNotif.not()
    }

    fun switchMailNotif(view: View) {
        settings.emailNotif = settings.emailNotif.not()
    }

}