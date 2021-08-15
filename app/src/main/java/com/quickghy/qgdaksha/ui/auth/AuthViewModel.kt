package com.quickghy.qgdaksha.ui.auth

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.quickghy.qgdaksha.MyApplication
import com.quickghy.qgdaksha.R
import com.quickghy.qgdaksha.data.auth.repositories.AuthUserRepository
import com.quickghy.qgdaksha.util.NoInternetException
import kotlinx.coroutines.launch
import java.lang.Exception


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
    var token: String? = null


    private var tNcflag = false

    //if otp for sign up needed, use flag 1, for password reset use flag 0
    private var flagForOTP = 0

    private val key = "DAKSHA_2020"

    var signUpStateListener: AuthStateListener.SignUpStateListener? = null
    var signUpOtpListener: AuthStateListener.SignUpOtpStateListener? = null
    var loginStateListener: AuthStateListener.LoginStateListener? = null
    var forgotPasswordStateListner: AuthStateListener.ForgotPasswordStateListner? = null
    private var resetOtpStateListener: AuthStateListener.ResetPassStateListener? = null
    var verifyOtpLoginStateListener: AuthStateListener.verifyLoginStateListener? = null

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
    fun goTologinotp(view: View) {
        view.findNavController().navigate(R.id.action_loginFragment_to_loginWithOtp)
    }
    fun goTologinpass(view: View) {
        view.findNavController().navigate(R.id.action_loginWithOtp_to_loginFragment)
    }


    fun doLoginWithPass(view: View) {

        if (phone.isNullOrEmpty() or password.isNullOrEmpty()) {
            loginStateListener?.onLoginFailure("Fields Can't be empty")
            return
        } else {
            loginStateListener?.onLoginStarted()
            // val loginResponse = AuthUserRepository().userLogin(phone!!, password!!,key) //tight cuppeld we just remove is using DI
            //loginStateListener?.onLoginSuccess(loginResponse)

            viewModelScope.launch {
                loginStateListener?.onLoginStarted()
                val loginrepo = repository.userLoginWithPass(phone!!, password!!)

                if (loginrepo == "true"){
                    loginStateListener?.onLoginSuccess("Login Success")
                    Log.d("true resp","true")
                }
                else{
                    loginStateListener?.onLoginFailure("Login Failure...")
                }


            }

        }

    }


    fun doSendOtpForlogin(view: View) {

        if (phone.isNullOrEmpty()) {
            loginStateListener?.onLoginFailure("Fields Can't be empty")
            return
        } else {
            view.isEnabled = false

            viewModelScope.launch {
                Log.d("req OTP","requesting for otp")
                val Respon = repository.SendSignupOtp(phone!!)
                if (Respon == "true") {
                    loginStateListener?.onLoginSuccess("OTP SENT SUCCESSFULLY")
                    flagForOTP = 0

                    Log.d("otp phone",phone!!)
                    view.findNavController()
                        .navigate(R.id.action_loginWithOtp_to_verifyOtpFragment)
                }
                else{
                    loginStateListener?.onLoginFailure(Respon)
                }
            }
        }

    }

    fun doVerifyOtp(view: View) {

        if (flagForOTP == 1) {
            //opens password reset screen
            verifyOtpLoginStateListener?.onLoginStarted()
            if (otp.isNullOrEmpty() || otp!!.length < 4) {
                resetOtpStateListener?.onFailReset("Enter correct otp")
            } else {
                //go to reset screen
                view.findNavController()
                    .navigate(R.id.action_verifyOtpFragment_to_resetPasswordFragment)
            }
        } else if (flagForOTP == 0) {

            loginStateListener?.onLoginStarted()

            Log.d("otp phone","jhgjhfgjhfjf")
            //results in sign up success.
            viewModelScope.launch {

                if (otp.isNullOrEmpty() || otp!!.length < 4) {
                    loginStateListener?.onLoginFailure("Enter correct otp")
                } else {

                    try {
                        val respon = repository.verifyOtpandLogin(phone!!, otp!!)
                        if (respon == "true") {
                            loginStateListener?.onLoginSuccess("Otp verified")
                        } else {
                            loginStateListener?.onLoginFailure(" Error Send Otp ")
                        }
                    }
                    catch (e:Exception){
                        Log.d("verify exp",e.toString())
                    }
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

    fun doGoogleLogin(Gtoken:String) {
        viewModelScope.launch {
            loginStateListener?.onLoginStarted()
            val googleauthresponse = repository.GoogleAuth(Gtoken)

            Log.d("true resp",googleauthresponse)
            if (googleauthresponse == "true"){
                loginStateListener?.onLoginSuccess("Login Success")
                Log.d("true resp","true")
            }
            else{
                loginStateListener?.onLoginFailure("Login Failure...")
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
