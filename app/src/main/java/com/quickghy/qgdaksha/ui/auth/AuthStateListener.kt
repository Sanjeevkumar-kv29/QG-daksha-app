package com.quickghy.qgdaksha.ui.auth

import androidx.lifecycle.LiveData

/**
 * @Author: Shubham Rimjha
 * @Date: 19-06-2021
 */
interface AuthStateListener {

    interface LoginStateListener {
        fun onLoginStarted()
        fun onLoginSuccess(loginResponse: LiveData<String>)
        fun onLoginFailure(message: String)
    }

    interface SignUpStateListener {
        fun onSignUpStarted()
        fun onSignUpSuccess()
        fun onSignUpFailure(message: String)

    }


}