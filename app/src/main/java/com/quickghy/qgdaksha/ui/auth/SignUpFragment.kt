package com.quickghy.qgdaksha.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.quickghy.qgdaksha.R
import com.quickghy.qgdaksha.databinding.FragmentSignUpBinding


class SignUpFragment : Fragment(), AuthStateListener.SignUpStateListener {

    lateinit var viewModel: AuthViewModel
    lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //so that it calls the viewmodel owned by the parent activity
        activity.let {
            viewModel = ViewModelProvider(it!!).get(AuthViewModel::class.java)
        }
        viewModel.signUpAuthListener = this

        binding.viewmodel = viewModel
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )
        return binding.root
    }

    override fun onSignUpStarted() {
        //put api to for
    }

    override fun onSignUpSuccess() {
        TODO("Not yet implemented")
    }

    override fun onSignUpFailure(message: String) {
        TODO("Not yet implemented")
    }

}