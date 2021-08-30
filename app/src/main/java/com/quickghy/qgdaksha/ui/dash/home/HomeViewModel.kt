package com.quickghy.qgdaksha.ui.dash.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    val token = MutableLiveData<String?>()
    val profile = homerepo.profileData
    val error = homerepo.err

    fun userlogincheck(){


        viewModelScope.launch {
            Log.d("tttttmodel","tttttmodel")
            val islogin = homerepo.isuserlogin()

            Log.d("tttttmodel",islogin.toString())

        }


    }

    fun getProfile() {
        viewModelScope.launch {
            homerepo.getProfile()
        }
    }

    fun readToken() {
        viewModelScope.launch {
            homerepo.getTokenFlow().collect {
                token.postValue(it)
            }
        }
    }

   /* fun getdata(token:String){

        viewModelScope.launch {
            val islogin = homerepo.getAndSaveData(token)

            if (islogin){
                Log.d("profile fetched","yes")

            }
            else{
                Log.d("profile fetched","no")
            }
        }


    }*/

}