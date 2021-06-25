package com.quickghy.qgdaksha.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.quickghy.qgdaksha.R
import com.quickghy.qgdaksha.data.auth.repositories.AuthUserRepository
import com.quickghy.qgdaksha.util.Coroutines
import kotlinx.coroutines.launch

/**
 * @Author: Shubham Rimjha, Sanjeev Kumar
 * @Date: 19-06-2021
 *
 */

//authViewModel is responsible for LoginFrag, SignUpFrag, VerifyOtpFrag screens
class AuthViewModel : ViewModel() {

    var phone: String? = null
    var password: String? = null
    var username: String? = null
    var otp: String? = null

    val key = "DAKSHA_2020"

    var signUpStateListener: AuthStateListener.SignUpStateListener? = null
    var loginStateListener: AuthStateListener.LoginStateListener? = null
    var forgotPasswordStateListner: AuthStateListener.ForgotPasswordStateListner? = null
    var signUpOtpStateListener: AuthStateListener.SignUpOtpStateListener? = null

    fun onSignInButtonClicked(view: View) {
        view.findNavController().navigate(R.id.action_loginFrag_to_signUpFrag)
    }

    fun ForgotPasswordButtonClicked(view: View) {
        view.findNavController().navigate(R.id.action_loginFrag_to_forgotPassFrag)
    }

    fun onLoginButtonClicked(view: View) {
        // Sanjeevs @TODO

        loginStateListener?.onLoginStarted()

        if (phone.isNullOrEmpty() or password.isNullOrEmpty()) {
            loginStateListener?.onLoginFailure("Fields Can't be empty")
            return
        } else {
            // val loginResponse = AuthUserRepository().userLogin(phone!!, password!!,key) //tight cuppeld we just remove is using DI
            //loginStateListener?.onLoginSuccess(loginResponse)
            viewModelScope.launch {
                val loginResponse = AuthUserRepository().userLogin(phone!!, password!!, key)
                if (loginResponse.isSuccessful) {
                    if (loginResponse.body()?.user_id == "error") {
                        loginStateListener?.onLoginFailure(loginResponse.body()?.opt!!)
                    } else {
                        loginStateListener?.onLoginSuccess(loginResponse.body()?.opt!!)
                    }

                }
            }
        }


    }

    fun onForgotPasswordButtonClicked(view: View) {
        // Navigation.findNavController(view).navigate()

        forgotPasswordStateListner?.onStart()

        if (phone.isNullOrEmpty()) {
            forgotPasswordStateListner?.onFailure("Fields Can't be empty")
            return
        } else {
            // val loginResponse = AuthUserRepository().userLogin(phone!!, password!!,key) //tight cuppeld we just remove is using DI
            //loginStateListener?.onLoginSuccess(loginResponse)

            Coroutines.main {
                val forgetResponse = AuthUserRepository().userForgetPass(phone!!, key)
                if (forgetResponse.isSuccessful) {

                    forgotPasswordStateListner?.onSuccess(forgetResponse.body()?.opt!!)

                }
            }
        }

    }


    fun onSignUpNowButtonClicked(view: View) {

        signUpStateListener?.onSignUpStarted()
        if (phone.isNullOrEmpty() or password.isNullOrEmpty() or username.isNullOrEmpty()) {
            signUpStateListener?.onSignUpFailure("Fields Can't be empty")
        } else {
            Coroutines.main {

                val signUpResponse =
                    AuthUserRepository().userSignUp(username!!, phone!!, password!!, key)

                if (signUpResponse.body()?.opt is Int) {//if the response opt is a number, it is success
                    signUpStateListener?.onSignUpSuccess(signUpResponse.body()?.opt.toString())
                } else if (signUpResponse.body()?.opt !is Int) {
                    signUpStateListener?.onSignUpFailure(signUpResponse.body()?.opt.toString())
                }
            }
        }
    }

    fun onVerifyOtpButtonClicked(view: View) {

        signUpOtpStateListener?.onSignUpOtpStarted()
        if (otp.isNullOrEmpty() || phone.isNullOrEmpty()) {
            signUpOtpStateListener?.onSignUpOtpFailure("Enter otp")
        } else {
            Coroutines.main {
                val signUpOtpResponse = AuthUserRepository().userSignUpOtp(phone!!, otp!!, key)

                if (signUpOtpResponse.body()?.opt !is String) {//if the response opt is a number, it is success
                    signUpStateListener?.onSignUpSuccess(signUpOtpResponse.body()?.opt.toString())
                } else if (signUpOtpResponse.body()?.opt is String) {
                    signUpStateListener?.onSignUpFailure(signUpOtpResponse.body()?.opt.toString())
                }
            }
        }
    }
}