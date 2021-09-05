package com.quickghy.qgdaksha.ui.auth

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.quickghy.qgdaksha.R
import com.quickghy.qgdaksha.data.auth.network.request.UserData
import com.quickghy.qgdaksha.data.auth.repositories.AuthUserRepository
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
    var userdata : UserData? = null


    private var tNcflag = false

    var signUpStateListener: AuthStateListener.SignUpStateListener? = null
    var loginStateListener: AuthStateListener.LoginStateListener? = null
    var forgotPasswordStateListner: AuthStateListener.ForgotPasswordStateListner? = null
    var loginOTPStateListener: AuthStateListener.LoginOTPStateListener? = null
    var verifyOtpLoginStateListener: AuthStateListener.verifyLoginStateListener? = null
    var verifyOtpSignupStateListener: AuthStateListener.verifySignupStateListener? = null

    fun goToSignUp(view: View) {
        view.findNavController().navigate(R.id.action_loginFrag_to_signUpFrag)
    }

    fun goToForgotPass(view: View) {
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

            viewModelScope.launch {
                loginStateListener?.onLoginStarted()
                val loginrepo = repository.userLoginWithPass("+91"+phone!!, password!!)

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
            loginOTPStateListener?.onLoginOtpFailure("Fields Can't be empty")
            return
        } else {
            loginOTPStateListener?.onLoginOtpStarted()
            view.isEnabled = false

            viewModelScope.launch {
                Log.d("req OTP","requesting for otp")
                val Respon = repository.SendSignupOtp("+91"+phone!!)
                if (Respon == "true") {
                    loginOTPStateListener?.onLoginOtpSuccess("Otp Sent Successfully")
                    Log.d("otp phone",phone!!)
                    view.findNavController().navigate(R.id.action_loginWithOtp_to_verifyOtpFragment)
                }
                else{
                    loginOTPStateListener?.onLoginOtpFailure("an error encountered try again later")
                }
            }
        }
    }

    
    fun doVerifyOtpAndLogin(view: View) {

        if (otp.isNullOrEmpty() || otp!!.length < 4) {
            verifyOtpLoginStateListener?.onverifyLoginFailure("Enter correct otp")
        }
        else {
            Log.d("otp phone", phone.toString())

            verifyOtpLoginStateListener?.onverifyLoginStarted("Verifying OTP...")

            viewModelScope.launch {
                    try {
                        val respon = repository.verifyOtpandLogin("+91"+phone!!,otp!!)
                        if (respon == "true") {
                            Log.d("LoginWithOtp", "Success")
                            verifyOtpLoginStateListener?.onverifyLoginSuccess("Otp verified")

                        } else {
                            Log.d("LoginWithOtp", "Fail")
                            verifyOtpLoginStateListener?.onverifyLoginFailure(respon)
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
            signUpStateListener?.onSignUpStarted()
            viewModelScope.launch {

                val Respon = repository.SendSignupOtp("+91"+phone!!)
                if (Respon == "true") {
                    view.findNavController().navigate(R.id.action_signUpFragment_to_verifyOtpSignupFragment)
                    signUpStateListener?.onSignUpSuccess("OTP sent Succesfully")
                }
                else{
                    Log.d("LoginWithOtp", "Fail")
                    signUpStateListener?.onSignUpFailure("Some thing went wrong")
                }
            }
        }
    }


    fun doSignupNextStep(view: View){

        if (otp.isNullOrEmpty() || otp!!.length < 4){
            verifyOtpSignupStateListener?.onverifySignupFailure("Enter otp")
        }
        else{
                viewModelScope.launch {

                    verifyOtpSignupStateListener?.onverifySignupStarted()
                    userdata = UserData(email!!,username!!,password!!,"+91"+phone!!)

                    Log.d("userdata", userdata.toString()+otp)
                        val signUpResponse = repository.userSignUp(userdata , otp.toString())

                        if (signUpResponse == "true") {//if the response opt is a number, it is success
                            Log.d("signup Api", "singin Success")
                            verifyOtpSignupStateListener?.onverifySignupSuccess("Signup Successfully")
                            repository.settingSET()
                        } else {
                            Log.d("signup Api", " signing Fail")
                            Log.d("signup Api", signUpResponse)
                            verifyOtpSignupStateListener?.onverifySignupFailure("Something went wrong please try again after some time")
                        }
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

}
