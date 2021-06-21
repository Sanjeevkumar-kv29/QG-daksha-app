package com.quickghy.qgdaksha.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.ViewUtils
import com.quickghy.qgdaksha.modules.auth.util.LongToast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.quickghy.qgdaksha.R
import com.quickghy.qgdaksha.databinding.FragmentLoginBinding


class LoginFragment : Fragment(),AuthStateListener.LoginStateListener{

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentLoginBinding = DataBindingUtil.setContentView(requireActivity(),R.layout.fragment_login)
        val viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel

        viewModel.loginStateListener = this

        return inflater.inflate(R.layout.fragment_login, container, false)
    }



    override fun onLoginStarted() {
        Toast.makeText(context, "login start", Toast.LENGTH_SHORT).show()
    }

    override fun onLoginSuccess() {
        Toast.makeText(context, "login Success", Toast.LENGTH_SHORT).show()
    }

    override fun onLoginFailure(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}