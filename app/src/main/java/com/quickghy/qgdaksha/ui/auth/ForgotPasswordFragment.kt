package com.quickghy.qgdaksha.ui.auth

import android.hardware.input.InputManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethod
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.quickghy.qgdaksha.R
import com.quickghy.qgdaksha.databinding.FragmentForgotPasswordBinding
import com.quickghy.qgdaksha.util.toast

class ForgotPasswordFragment : Fragment(), AuthStateListener.ForgotPasswordStateListner {

    lateinit var viewModel: AuthViewModel
    lateinit var binding: FragmentForgotPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_forgot_password, container, false)

        activity.let {
            viewModel = ViewModelProvider(it!!).get(AuthViewModel::class.java)
        }

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_forgot_password,
            container,
            false
        )

        binding.viewmodel = viewModel
        viewModel.forgotPasswordStateListner = this
        return binding.root
    }


    override fun onSuccess(opt: String) {
        context?.toast(opt)

    }

    override fun onFailure(message: String) {
        context?.toast(message)
    }

}