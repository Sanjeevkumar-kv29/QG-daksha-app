package com.quickghy.qgdaksha.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.quickghy.qgdaksha.R
import com.quickghy.qgdaksha.databinding.FragmentVerifyOtpBinding

class VerifyOtpFragment : Fragment(), AuthStateListener.SignUpOtpStateListener {

    lateinit var viewModel: AuthViewModel
    lateinit var binding: FragmentVerifyOtpBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_verify_otp,
            container,
            false
        )

        activity.let {
            viewModel = ViewModelProvider(it!!).get(AuthViewModel::class.java)
        }

        binding.viewmodel = viewModel
        viewModel.signUpOtpStateListener = this


        return inflater.inflate(R.layout.fragment_verify_otp, container, false)
    }

    override fun onSignUpOtpStarted() {
        TODO("Not yet implemented")
//        update ui or do other stuff
    }

    override fun onSignUpOtpSuccess(opt: String) {
        Toast.makeText(context,opt + ": sign up success!", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_verifyOtpFragment_to_loginFragment)
    }

    override fun onSignUpOtpFailure(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}