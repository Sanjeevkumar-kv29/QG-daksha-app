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
import com.quickghy.qgdaksha.data.auth.repositories.userData
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
    var email: String? = null
    var userdata : userData? = null


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

        if (otp.isNullOrEmpty() || otp!!.length < 4) {
            loginStateListener?.onLoginFailure("Enter correct otp")
        }
        else {
            Log.d("otp phone", phone.toString())

            loginStateListener?.onLoginStarted()

            viewModelScope.launch {
                    try {
                        val respon = repository.verifyOtpandLogin( otp!!)
                        if (respon == "true") {
                            Log.d("LoginWithOtp", "Success")
                            loginStateListener?.onLoginSuccess("Otp verified")
                            view.findNavController().navigate(R.id.action_verifyOtpFragment_to_loginFragment)

                        } else {
                            Log.d("LoginWithOtp", "Fail")
                            loginStateListener?.onLoginFailure(" Error Send Otp ")
                        }
                    }
                    catch (e:Exception){
                        Log.d("verify exp",e.toString())
                    }

                }
            }

    }



    fun doSignUp(view: View) {

        if (phone.isNullOrEmpty() or password.isNullOrEmpty() or username.isNullOrEmpty() or email.isNullOrEmpty()) {
            signUpStateListener?.onSignUpFailure("Fields Can't be empty.")
        } else if (!password.equals(repassword)) {
            signUpStateListener?.onSignUpFailure("Passwords do not match.")
        } else if (!tNcflag) {
            signUpStateListener?.onSignUpFailure("Please accept T&C")
        } else {
             userdata = userData(fullname,phone,email,password)
            viewModelScope.launch {

                val Respon = repository.SendSignupOtp(phone!!)
                if (Respon == "true") {
                    loginStateListener?.onLoginSuccess("OTP SENT SUCCESSFULLY")
                    view.findNavController().navigate(R.id.action_signUpFragment_to_verifyOtpSignupFragment)
                }
                else{
                    signUpStateListener?.onSignUpFailure(Respon)
                }


            }
        }
    }

    fun doSignupNextStep(view: View){
           try {
                viewModelScope.launch {
                    Log.d("userdata", phone + email + fullname + password )

                    Log.d("userdata", userdata.toString())
                        val signUpResponse = repository.userSignUp(userdata!!, otp!!)

                        if (signUpResponse == "true") {//if the response opt is a number, it is success
                            Log.d("signup Api", "singin Success")
                        } else {
                            Log.d("signup Api", " signing Fail")

                        }
                    }
                }
                catch(e:Exception) {
                    Log.d("verify exp", e.toString())
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
