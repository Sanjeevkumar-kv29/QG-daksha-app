package com.quickghy.qgdaksha.ui.auth

import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.quickghy.qgdaksha.R
import com.quickghy.qgdaksha.data.auth.repositories.AuthUserRepository
import com.quickghy.qgdaksha.util.NoInternetException
import kotlinx.coroutines.launch


/**
 * @Author: Shubham Rimjha, Sanjeev Kumar
 * @Date: 19-06-2021
 *
 */

//authViewModel is responsible for LoginFrag, SignUpFrag, VerifyOtpFrag screens
class AuthViewModel(

    val repository: AuthUserRepository

) : ViewModel() {


    var fullname: String? = null
    var phone: String? = null
    var password: String? = null
    var repassword: String? = null
    var username: String? = null
    var otp: String? = null

    private var tNcflag = false

    //if otp for sign up needed, use flag 1, for password reset use flag 0
    private var flagForOTP = 0

    private val key = "DAKSHA_2020"

    var signUpStateListener: AuthStateListener.SignUpStateListener? = null
    var signUpOtpListener: AuthStateListener.SignUpOtpStateListener? = null
    var loginStateListener: AuthStateListener.LoginStateListener? = null
    var forgotPasswordStateListner: AuthStateListener.ForgotPasswordStateListner? = null
    private var resetOtpStateListener: AuthStateListener.ResetPassStateListener? = null

    fun goToSignUp(view: View) {
        view.findNavController().navigate(R.id.action_loginFrag_to_signUpFrag)
    }

    fun goToForgotPass(view: View) {
        flagForOTP = 1
        view.findNavController().navigate(R.id.action_loginFrag_to_forgotPassFrag)
    }

    fun goToLogin(view: View) {
        view.findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
    }

    fun help(view: View) {
        view.findNavController().navigate(R.id.helpFragment)
    }

    fun doLogin(view: View) {

        if (phone.isNullOrEmpty() or password.isNullOrEmpty()) {
            loginStateListener?.onLoginFailure("Fields Can't be empty")
            return
        } else {
            loginStateListener?.onLoginStarted()
            // val loginResponse = AuthUserRepository().userLogin(phone!!, password!!,key) //tight cuppeld we just remove is using DI
            //loginStateListener?.onLoginSuccess(loginResponse)
            viewModelScope.launch {
                try {
                    val loginResponse = repository.userLogin(phone!!, password!!, key)
                    if (loginResponse.isSuccessful) {
                        if (loginResponse.body()?.user_id == "error") {
                            loginStateListener?.onLoginFailure(loginResponse.body()?.opt!!)
                        } else {
                            loginStateListener?.onLoginSuccess(loginResponse.body()?.opt!!)
                            repository.saveDATAtoDS(loginResponse.body()?.user_id.toString(),
                                                    loginResponse.body()?.user_name.toString(),
                                                    loginResponse.body()?.access_token.toString(),
                                                    phone!!)
                        }
                    }
                }
                catch (e:NoInternetException){
                    loginStateListener?.onLoginNetworkFailure(e.message.toString())
                }

            }

        }

    }


    fun doSendOTP(view: View) {

        if (phone.isNullOrEmpty()) {
            forgotPasswordStateListner?.onFailureForgot("Fields Can't be empty")
            return
        } else {
            view.isEnabled = false
            // val loginResponse = AuthUserRepository().userLogin(phone!!, password!!,key) //tight cuppeld we just remove is using DI
            //loginStateListener?.onLoginSuccess(loginResponse)

            viewModelScope.launch {
                val forgetResponse = repository.userForgetPass(phone!!, key)
                if (forgetResponse.isSuccessful) {
                    forgotPasswordStateListner?.onSuccessForgot(forgetResponse.body()?.opt!!)
                    view.findNavController()
                        .navigate(R.id.action_forgotPasswordFragment_to_verifyOtpFragment)
                }
            }
        }

    }


    fun doSignUp(view: View) {

        signUpStateListener?.onSignUpStarted()
        if (phone.isNullOrEmpty() or password.isNullOrEmpty() or username.isNullOrEmpty()) {
            signUpStateListener?.onSignUpFailure("Fields Can't be empty.")
        } else if (!password.equals(repassword)) {
            signUpStateListener?.onSignUpFailure("Passwords do not match.")
        } else if (!tNcflag) {
            signUpStateListener?.onSignUpFailure("Please accept T&C")
        } else {
            viewModelScope.launch {
                val signUpResponse =
                    repository.userSignUp(username!!, phone!!, password!!, key)

                if (signUpResponse.isSuccessful) {//if the response opt is a number, it is success
                    if (signUpResponse.body()?.opt.toString() == "Already Registered Phone Number") signUpStateListener?.onSignUpFailure(
                        signUpResponse.body()?.opt.toString()
                    )
                    else {
                        signUpStateListener?.onSignUpSuccess(signUpResponse.body()?.opt.toString())
                        view.findNavController().navigate(R.id.action_signUpFrag_to_OtpFrag)
                    }
                }
            }
        }
    }


    fun doVerifyOTP(view: View) {

        if (flagForOTP == 1) {
            //opens password reset screen
            resetOtpStateListener?.onStartReset()
            if (otp.isNullOrEmpty() || otp!!.length < 6) {
                resetOtpStateListener?.onFailReset("Enter correct otp")
            } else {
                //go to reset screen
                view.findNavController()
                    .navigate(R.id.action_verifyOtpFragment_to_resetPasswordFragment)
            }
        } else if (flagForOTP == 0) {
            //results in sign up success.
            viewModelScope.launch {
                val signUpOtpResponse = repository.userSignUpOtp(phone!!, otp!!, key)
                if (signUpOtpResponse.isSuccessful) {
                    signUpStateListener?.onSignUpSuccess(signUpOtpResponse.body()?.access_token.toString())
                    putaccesstokenintoDB(signUpOtpResponse.body()?.access_token.toString())
                }
            }
        }
    }


    fun doUpdatePass(view: View) {
        resetOtpStateListener?.onStartReset()

        if (password.isNullOrEmpty() || !password.equals(repassword)) {
            resetOtpStateListener?.onFailReset("Passwords don't match or are empty")
        } else {
            viewModelScope.launch {
                val signUpOtpResponse =
                    repository.userResetPass(phone!!, otp!!, password!!, key)
                if (signUpOtpResponse.body()?.opt.toString() == "1") {//if the response opt is a numb// er, it is success
                    resetOtpStateListener?.onSuccessReset(signUpOtpResponse.body()?.opt.toString())
                    view.findNavController()
                        .navigate(R.id.action_resetPasswordFragment_to_loginFragment)
                } else
                    resetOtpStateListener?.onFailReset("Password Update failed...")
            }
        }
    }

    fun goBack(view: View) {
        view.findNavController().popBackStack()
    }

    fun goBackToForgot(view: View) {
        view.findNavController().popBackStack(R.id.forgotPasswordFragment, false)
    }

    fun updatetncflag(view: View) {
        tNcflag = !tNcflag
    }

    private fun putaccesstokenintoDB(toString: String) {
        //call DB
    }

}
