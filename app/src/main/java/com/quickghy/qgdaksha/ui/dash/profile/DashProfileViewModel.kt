package com.quickghy.qgdaksha.ui.dash.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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



    var userloggedinstatelistner: DashStateListener.IsUserLoggedin?  = null


    fun Loginstatus() {

        viewModelScope.launch {
            val islogin = repository.isuserlogincheck()

            if (islogin){
                Log.d("userloggin","user logged in")
                userloggedinstatelistner?.yes()

            }
            else{
                Log.d("userloggin","user not logged in")
                userloggedinstatelistner?.no()
            }
        }


    }


}
