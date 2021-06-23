package com.quickghy.qgdaksha.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.quickghy.qgdaksha.R
import com.quickghy.qgdaksha.databinding.FragmentLoginBinding
import com.quickghy.qgdaksha.util.toast


class LoginFragment : Fragment(), AuthStateListener.LoginStateListener {

    lateinit var viewModel: AuthViewModel
    lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )

        //so that it calls the viewmodel owned by the parent activity
        activity.let {
            viewModel = ViewModelProvider(it!!).get(AuthViewModel::class.java)
        }

        viewModel.loginStateListener = this
        binding.viewmodel = viewModel

        viewModel.loginStateListener = this

        binding.btnLogin.setOnClickListener {
            viewModel.onLoginButtonClicked(it)
            findNavController().navigate(R.id.action_loginFrag_to_signUpFrag)
        }
        binding.btnForgotPass.setOnClickListener {
            viewModel.onForgotPasswordButtonClicked(it)
            findNavController().navigate(R.id.action_loginFrag_to_forgotPassFrag)
        }
        binding.btnGoToSignup.setOnClickListener {
            viewModel.onForgotPasswordButtonClicked(it)
            findNavController().navigate(R.id.action_loginFrag_to_signUpFrag)
        }

        return binding.root

    }

    override fun onLoginStarted() {
        // put api call for login here
        context?.toast("login Start")
    }


    override fun onLoginSuccess(successRes: String) {
        context?.toast(successRes)
//        activity.startActivity("Home Page...)
    }

    override fun onLoginFailure(message: String) {
        // display failure message toast
        context?.toast(message)
    }


}