package com.quickghy.qgdaksha.ui.auth

import androidx.lifecycle.LiveData

/**
 * @Author: Shubham Rimjha
 * @Date: 19-06-2021
 */
interface AuthStateListener {

    interface LoginStateListener {
        fun onLoginStarted()
        fun onLoginSuccess(successRes: String)
        fun onLoginFailure(message: String)
    }

    interface SignUpStateListener {
        fun onSignUpStarted()
        fun onSignUpSuccess(SignUpResponse: LiveData<String>)
        fun onSignUpFailure(message: String)

    }

    interface ForgotPasswordStateListner {
        fun onStart()
        fun onSuccess(opt: String)
        fun onFailure(message: String)

    }


}