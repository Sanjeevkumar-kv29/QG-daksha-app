package com.quickghy.qgdaksha.ui.auth

import android.content.Intent
import android.hardware.input.InputManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieAnimationView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.quickghy.qgdaksha.R
import com.quickghy.qgdaksha.databinding.FragmentLoginBinding
import com.quickghy.qgdaksha.databinding.FragmentLoginWithOtpBinding
import com.quickghy.qgdaksha.ui.dash.DashActivity
import com.quickghy.qgdaksha.util.toast
import org.json.JSONObject
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.java.KoinJavaComponent.inject


class LoginWithOtp : Fragment(), AuthStateListener.verifyLoginStateListener {

    lateinit var binding: FragmentLoginWithOtpBinding
    private val viewModel by sharedViewModel<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login_with_otp,
            container,
            false
        )

        binding.viewmodel = viewModel
        viewModel.verifyOtpLoginStateListener = this

        return binding.root

    }


    override fun onverifyLoginStarted(successRes: String) {
        // put api call for login here
        context?.toast(successRes)
    }


    override fun onverifyLoginSuccess(successRes: String) {
        context?.toast(successRes)
        startActivity(Intent(context,DashActivity::class.java))
        requireActivity().finish()
    }

    override fun onverifyLoginFailure(message: String) {
        // display failure message toast
        context?.toast(message)
    }

    override fun onverifyLoginNetworkFailure(message: String) {
        context?.toast(message)
    }



}