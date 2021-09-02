package com.quickghy.qgdaksha.ui.dash.profile

import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.quickghy.qgdaksha.data.dash.profile.repositories.ProfileRepository
import com.quickghy.qgdaksha.ui.dash.DashStateListener
import kotlinx.coroutines.launch


/**
 * @Author: Sanjeev Kumar
 * @Date: 10-07-2021
 *
 */

class DashProfileViewModel(
    val repository: ProfileRepository
) : ViewModel() {

    val getprofile = repository.userprofileData
    val profiletoken = repository.profiletoken



}
