package com.quickghy.qgdaksha.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment

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

    fun onSignInButtonClicked(view: View) {
        //  verify fields.
        signUpAuthListener?.onSignUpStarted()
        signUpAuthListener?.onSignUpSuccess()
        signUpAuthListener?.onSignUpFailure()

    }

    fun onLoginButtonClicked(view: View) {
        // Sanjeevs @TODO
        loginStateListener?.onLoginStarted()
        loginStateListener?.onLoginSuccess()
        loginStateListener?.onLoginFailure()
    }

    fun onForgotPasswordButtonClicked(view: View){
        Navigation.findNavController(view).navigate()
    }

    fun onSignUpNowButtonClicked(){

    }
}