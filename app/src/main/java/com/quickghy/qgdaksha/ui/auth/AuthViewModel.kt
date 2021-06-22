package com.quickghy.qgdaksha.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.quickghy.qgdaksha.data.auth.repositories.AuthUserRepository
import com.quickghy.qgdaksha.util.Coroutines

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
    var signUpAuthListener: AuthStateListener.SignUpStateListener? = null
    var loginStateListener: AuthStateListener.LoginStateListener? = null

    fun onSignInButtonClicked(view: View) {
        //  verify fields.
        signUpAuthListener?.onSignUpStarted()
        signUpAuthListener?.onSignUpSuccess()
       // signUpAuthListener?.onSignUpFailure()

    }

    fun onLoginButtonClicked(view: View) {
        // Sanjeevs @TODO

        var key = "DAKSHA_2020"
        loginStateListener?.onLoginStarted()

        if (phone.isNullOrEmpty() or password.isNullOrEmpty()){
            loginStateListener?.onLoginFailure("Fields Can't be empty")
            return
        }
                else{
           // val loginResponse = AuthUserRepository().userLogin(phone!!, password!!,key) //tight cuppeld we just remove is using DI
            //loginStateListener?.onLoginSuccess(loginResponse)

                Coroutines .main {
                    val loginResponse = AuthUserRepository().userLogin(phone!!, password!!,key)
                    if (loginResponse.isSuccessful){
                        if (loginResponse.body()?.user_id == "error"){
                            loginStateListener?.onLoginFailure(loginResponse.body()?.opt!!)
                        }
                        else{
                            loginStateListener?.onLoginSuccess(loginResponse.body()?.opt!!)
                        }

                    }
                }
        }



    }

    fun onForgotPasswordButtonClicked(view: View){
       // Navigation.findNavController(view).navigate()
    }

    fun onSignUpNowButtonClicked(view: View){

    }
}