package com.quickghy.qgdaksha.modules.auth.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.quickghy.qgdaksha.R


class SignUpFragment : Fragment(), AuthStateListener.SignUpStateListener {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)

    }

    override fun onSignUpStarted() {
        TODO("Not yet implemented")
    }

    override fun onSignUpSuccess() {
        TODO("Not yet implemented")
    }

    override fun onSignUpFailure() {
        TODO("Not yet implemented")
    }

}