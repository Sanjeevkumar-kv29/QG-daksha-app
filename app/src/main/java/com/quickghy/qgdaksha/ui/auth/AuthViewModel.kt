package com.quickghy.qgdaksha.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.quickghy.qgdaksha.R
import com.quickghy.qgdaksha.data.auth.repositories.AuthUserRepository
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
    var repassword: String? = null
    var username: String? = null
    var otp: String? = null

    val key = "DAKSHA_2020"

    var signUpStateListener: AuthStateListener.SignUpStateListener? = null
    var loginStateListener: AuthStateListener.LoginStateListener? = null
    var forgotPasswordStateListner: AuthStateListener.ForgotPasswordStateListner? = null
    var resetOtpStateListener: AuthStateListener.SignUpOtpStateListener? = null

    fun goToSignUp(view: View) {
        view.findNavController().navigate(R.id.action_loginFrag_to_signUpFrag)
    }

    fun goToForgotPass(view: View) {
        view.findNavController().navigate(R.id.action_loginFrag_to_forgotPassFrag)
    }

    fun goToLogin(view: View) {
        view.findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
    }

    fun doLogin(view: View) {
        // Sanjeevs @TODO

        if (phone.isNullOrEmpty() or password.isNullOrEmpty()) {
            loginStateListener?.onLoginFailure("Fields Can't be empty")
            return
        } else {
            loginStateListener?.onLoginStarted()
            // val loginResponse = AuthUserRepository().userLogin(phone!!, password!!,key) //tight cuppeld we just remove is using DI
            //loginStateListener?.onLoginSuccess(loginResponse)
            viewModelScope.launch {
                val loginResponse = AuthUserRepository().userLogin(phone!!, password!!, key)
                if (loginResponse.isSuccessful) {
                    if (loginResponse.body()?.user_id == "error") {
                        loginStateListener?.onLoginFailure(loginResponse.body()?.opt!!)
                    } else {
                        loginStateListener?.onLoginSuccess(loginResponse.body()?.opt!!)

                        // this is the exit point from this activity.
                        finishThisAndGoToHome()//-----------------------------------------
                    }

                }
            }
        }


    }


    fun doSendOTP (view: View) {
        // Navigation.findNavController(view).navigate()

        forgotPasswordStateListner?.onStart()

        if (phone.isNullOrEmpty()) {
            forgotPasswordStateListner?.onFailure("Fields Can't be empty")
            return
        } else {
            // val loginResponse = AuthUserRepository().userLogin(phone!!, password!!,key) //tight cuppeld we just remove is using DI
            //loginStateListener?.onLoginSuccess(loginResponse)

            viewModelScope.launch {
                val forgetResponse = AuthUserRepository().userForgetPass(phone!!, key)
                if (forgetResponse.isSuccessful) {
                    forgotPasswordStateListner?.onSuccess(forgetResponse.body()?.opt!!)
                    view.findNavController()
                        .navigate(R.id.action_forgotPasswordFragment_to_verifyOtpFragment)

                }
            }
        }

    }


    fun doSignUp(view: View) {

        signUpStateListener?.onSignUpStarted()
        if (phone.isNullOrEmpty() or password.isNullOrEmpty() or username.isNullOrEmpty()) {
            signUpStateListener?.onSignUpFailure("Fields Can't be empty")
        } else {
            viewModelScope.launch {
                val signUpResponse =
                    AuthUserRepository().userSignUp(username!!, phone!!, password!!, key)

                if (signUpResponse.isSuccessful) {//if the response opt is a number, it is success
                    signUpStateListener?.onSignUpSuccess(signUpResponse.body()?.opt.toString())
                    view.findNavController().navigate(R.id.action_signUpFrag_to_OtpFrag)
                }
            }
        }
    }


    fun doVerifyOTP(view: View) {
        resetOtpStateListener?.onSignUpOtpStarted()
        if (otp.isNullOrEmpty() || phone.isNullOrEmpty()) {
            resetOtpStateListener?.onResetOtpFailure("Enter otp")
        } else {
            viewModelScope.launch {
                val signUpOtpResponse = AuthUserRepository().userSignUpOtp(phone!!, otp!!, key)

                if (signUpOtpResponse.isSuccessful) {//if the response opt is success, password has been reset.
                    signUpStateListener?.onSignUpSuccess(signUpOtpResponse.body()?.access_token.toString())
                    putaccesstokenintoDB(signUpOtpResponse.body()?.access_token.toString())
                    finishThisAndGoToHome()//go to home activity.

                }
            }
        }
    }


    fun doUpdatePass(view: View) {
        if (otp.isNullOrEmpty() || password.isNullOrEmpty()) {
            resetOtpStateListener?.onResetOtpFailure("OTP or Password incorrect")
        } else {
            viewModelScope.launch {
                val signUpOtpResponse =
                    AuthUserRepository().userResetPass(phone!!, otp!!, password!!, key)

                if (signUpOtpResponse.isSuccessful) {//if the response opt is a numb// er, it is success
                    resetOtpStateListener?.onSignUpOtpSuccess(signUpOtpResponse.body()?.opt.toString())
                    view.findNavController()
                        .navigate(R.id.action_verifyOtpFragment_to_loginFragment)
                }
            }
        }
    }

    fun finishThisAndGoToHome() {
        //this is where you navigate to the home page...--------------------------------------------
        // also save the user into the dataStore here
    }

    private fun putaccesstokenintoDB(toString: String) {
        //call
    }


}
