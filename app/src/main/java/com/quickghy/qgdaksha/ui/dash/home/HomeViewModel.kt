package com.quickghy.qgdaksha.ui.dash.home

import android.util.Log
import androidx.lifecycle.*
import com.quickghy.qgdaksha.data.dash.home.repositories.HomeRepository
import com.quickghy.qgdaksha.data.dash.profile.network.Response.GetProfileResponse
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * @Author: Sanjeev Kumar
 * @Date: 27-07-2021
 */

class HomeViewModel(
    val homerepo :HomeRepository
) : ViewModel() {

    val profile = homerepo.userprofileData
    val token = homerepo.token

    fun checkprofile(){

        viewModelScope.launch {
            val check =  homerepo.getTokenFlow()
        }

    }

}