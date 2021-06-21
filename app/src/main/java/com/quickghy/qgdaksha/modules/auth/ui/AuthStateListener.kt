package com.quickghy.qgdaksha.modules.auth.ui

/**
 * @Author: Shubham Rimjha
 * @Date: 19-06-2021
 */
interface AuthStateListener {

    interface LoginStateListener {
        fun onLoginStarted()
        fun onLoginSuccess()
        fun onLoginFailure(message:String)
    }

    interface SignUpStateListener {
        fun onSignUpStarted()
        fun onSignUpSuccess()
        fun onSignUpFailure()

    }


}