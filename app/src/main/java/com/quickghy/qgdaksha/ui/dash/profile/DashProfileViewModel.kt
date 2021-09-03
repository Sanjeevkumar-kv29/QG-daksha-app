package com.quickghy.qgdaksha.ui.dash.profile

import android.view.View
import androidx.lifecycle.*
import com.quickghy.qgdaksha.data.dash.profile.repositories.ProfileRepository


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
