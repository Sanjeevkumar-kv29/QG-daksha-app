package com.quickghy.qgdaksha.modules.auth.ui

import android.view.View
import androidx.lifecycle.ViewModel

/**
 * @Author: Shubham Rimjha
 * @Date: 19-06-2021
 */

//authViewModel is responsible for LoginFrag, SignUpFrag, VerifyOtpFrag screens
class AuthViewModel : ViewModel() {

    var phone: String? = null
    var password: String? = null
    var username: String? = null
    var signUpAuthListener: AuthStateListener.SignUpStateListener? = null
    var loginStateListener: AuthStateListener.LoginStateListener? = null

    fun onSignInButtonClicked() {
        //  verify fields.
    }

    fun onLoginButtonClicked(view: View) {
        // Sanjeevs @TODO
    }
}