package com.quickghy.qgdaksha.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.quickghy.qgdaksha.R
import com.quickghy.qgdaksha.databinding.FragmentForgotPasswordBinding

class ForgotPasswordFragment : Fragment(),AuthStateListener.ForgotPasswordStateListner {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentForgotPasswordBinding = DataBindingUtil.setContentView(requireActivity(),R.layout.fragment_forgot_password)
        val viewModel = ViewModelProviders.of(this@ForgotPasswordFragment).get(AuthViewModel::class.java)

        binding.forgotviewmodel = viewModel

        viewModel.forgotPasswordStateListner = this





        return inflater.inflate(R.layout.fragment_forgot_password, container, false)
    }

    override fun onSuccess(opt: String) {

        Toast.makeText(context, opt , Toast.LENGTH_SHORT).show()
    }

    override fun onFailure(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}