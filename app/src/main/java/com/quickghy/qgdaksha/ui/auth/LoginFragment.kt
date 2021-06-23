package com.quickghy.qgdaksha.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.quickghy.qgdaksha.R
import com.quickghy.qgdaksha.databinding.FragmentLoginBinding


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

//        binding.btnForgotPass.setOnClickListener { view ->
//            viewModel.onForgotPasswordButtonClicked()
//        }
//        binding.btnGoToSignup.setOnClickListener { view ->
//            viewModel.onForgotPasswordButtonClicked()
//            view.findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
//        }


        return binding.root

    }

    override fun onLoginStarted() {
        // put api call for login here
        Toast.makeText(context, "login start", Toast.LENGTH_SHORT).show()
    }


    override fun onLoginSuccess(successRes: String) {
        Toast.makeText(context, successRes, Toast.LENGTH_SHORT).show()
    }

    override fun onLoginFailure(message: String) {
        // display failure message toast
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}