package com.quickghy.qgdaksha.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.quickghy.qgdaksha.R
import com.quickghy.qgdaksha.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment(), AuthStateListener.SignUpStateListener {

    lateinit var viewModel: AuthViewModel
    lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)

        activity.let {
            viewModel = ViewModelProvider(it!!).get(AuthViewModel::class.java)
        }

        binding.viewmodel = viewModel

        viewModel.signUpStateListener = this

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_sign_up,
            container,
            false
        )
        return view
    }

    override fun onSignUpStarted() {
        //put api to for
    }

    override fun onSignUpSuccess(SignUpResponse: LiveData<String>) {
        TODO("Not yet implemented")
    }


    override fun onSignUpFailure(message: String) {
        TODO("Not yet implemented")
    }

}