package com.quickghy.qgdaksha.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.quickghy.qgdaksha.R
import com.quickghy.qgdaksha.databinding.FragmentForgotPasswordBinding

class ResetPasswordFragment : Fragment() {

    lateinit var viewModel: AuthViewModel
    lateinit var binding: FragmentForgotPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_reset_password)

        activity.let {
            viewModel = ViewModelProvider(it!!).get(AuthViewModel::class.java)
        }
        binding.forgotviewmodel = viewModel

        viewModel.forgotPasswordStateListner = this



        return binding.root
    }

}