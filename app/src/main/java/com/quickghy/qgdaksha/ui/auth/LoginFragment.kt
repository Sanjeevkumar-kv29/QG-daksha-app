package com.quickghy.qgdaksha.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.quickghy.qgdaksha.R
import com.quickghy.qgdaksha.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment(), AuthStateListener.LoginStateListener {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentLoginBinding = DataBindingUtil.setContentView(requireActivity(),R.layout.fragment_login)
        val viewModel = ViewModelProviders.of(this@LoginFragment).get(AuthViewModel::class.java)

        binding.viewmodel = viewModel

        viewModel.loginStateListener = this

        binding.btnForgotPass.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }
        binding.btnGoToSignup.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }


        return inflater.inflate(R.layout.fragment_login, container, false)

    }

    override fun onLoginStarted() {
        // put api call for login here

        Toast.makeText(context, "login start", Toast.LENGTH_SHORT).show()

    }


    override fun onLoginSuccess(loginResponse: String) {

            Toast.makeText(context, loginResponse, Toast.LENGTH_SHORT).show()


    }

    override fun onLoginFailure(message: String) {
        // display failure message toast


        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}