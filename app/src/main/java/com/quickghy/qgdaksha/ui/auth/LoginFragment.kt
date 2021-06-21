package com.quickghy.qgdaksha.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.quickghy.qgdaksha.R
import com.quickghy.qgdaksha.databinding.FragmentLoginBinding


class LoginFragment : Fragment(), AuthStateListener.LoginStateListener {
    lateinit var viewModel: AuthViewModel
    lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        binding.viewmodel = viewModel
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )

        binding.btnForgotPass.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }
        binding.btnGoToSignup.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
        binding.btnLogin.setOnClickListener { }

        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

    override fun onLoginStarted() {
        // put api call for login here
    }

    override fun onLoginSuccess() {
        // display success toast
    }

    override fun onLoginFailure(message: String) {
        // display failure message toast
    }
}