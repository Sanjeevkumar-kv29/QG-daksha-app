package com.quickghy.qgdaksha.ui.auth

/**
 * @Author: Shubham Rimjha
 * @Date: 19-06-2021
 */
interface AuthStateListener {

    interface LoginStateListener {
        fun onLoginStarted()
        fun onLoginSuccess(successRes: String)
        fun onLoginFailure(message: String)
        fun onLoginNetworkFailure(message: String)
    }

    interface verifyLoginStateListener {
        fun onverifyLoginStarted(successRes: String)
        fun onverifyLoginSuccess(successRes: String)
        fun onverifyLoginFailure(message: String)
        fun onverifyLoginNetworkFailure(message: String)
    }

    interface verifySignupStateListener {
        fun onverifySignupStarted()
        fun onverifySignupSuccess(successRes: String)
        fun onverifySignupFailure(message: String)
    }

    interface SignUpStateListener {
        fun onSignUpStarted()
        fun onSignUpSuccess(SignUpResponse: String)
        fun onSignUpFailure(message: String)
    }

    interface ForgotPasswordStateListner {
        fun onSuccessForgot(opt: String)
        fun onFailureForgot(message: String)
    }

    interface ResetPassStateListener {
        fun onStartReset()
        fun onSuccessReset(opt: String)
        fun onFailReset(message: String)
    }





}