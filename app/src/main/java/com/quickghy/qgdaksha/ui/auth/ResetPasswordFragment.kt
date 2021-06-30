package com.quickghy.qgdaksha.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.quickghy.qgdaksha.R
import com.quickghy.qgdaksha.databinding.FragmentResetPasswordBinding
import com.quickghy.qgdaksha.util.toast

class ResetPasswordFragment : Fragment(),AuthStateListener.SignUpOtpStateListener {

    lateinit var viewModel: AuthViewModel
    lateinit var binding: FragmentResetPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_reset_password, container, false)

        activity.let {
            viewModel = ViewModelProvider(it!!).get(AuthViewModel::class.java)
        }

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_reset_password,
            container,
            false
        )

        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onSignUpOtpStarted() {
        context?.toast("Updating your password")
    }

    override fun onSignUpOtpSuccess(opt: String) {
        context?.toast("Success code = $opt")
    }

    override fun onSignUpOtpFailure(message: String) {
        context?.toast("Failure: $message")
    }


}