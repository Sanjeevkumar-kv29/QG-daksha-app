package com.quickghy.qgdaksha.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

        binding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_login)
//        because the viewmodel was first created in the main activity.
        activity.let {
            viewModel = ViewModelProvider(it!!).get(AuthViewModel::class.java)
        }
//        ViewModelProviders.of(this@LoginFragment).get(AuthViewModel::class.java)

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