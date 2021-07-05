package com.quickghy.qgdaksha.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.airbnb.lottie.LottieAnimationView
import com.quickghy.qgdaksha.R
import com.quickghy.qgdaksha.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment(), AuthStateListener.SignUpStateListener {

    lateinit var viewModel: AuthViewModel
    lateinit var binding: FragmentSignUpBinding
    private lateinit var anim_btn: LottieAnimationView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

//        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)

        activity.let {
            viewModel = ViewModelProvider(it!!).get(AuthViewModel::class.java)
        }

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_sign_up,
            container,
            false
        )

        binding.viewmodel = viewModel
        anim_btn = binding.btnAnimWheel

        viewModel.signUpStateListener = this


        return binding.root
    }

    override fun onSignUpStarted() {
        //put api to for
//        show progress bar here
        anim_btn.visibility = View.VISIBLE
        binding.btnAnimSignUp.visibility = View.INVISIBLE
        anim_btn.playAnimation()
    }

    override fun onSignUpSuccess(SignUpResponse: String) {
        Toast.makeText(context, SignUpResponse, Toast.LENGTH_SHORT).show()
        anim_btn.visibility = View.INVISIBLE
        binding.btnAnimSignUp.visibility = View.VISIBLE
        anim_btn.pauseAnimation()
//        do nav here to the otp screen
    }


    override fun onSignUpFailure(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
//        toast error and do nothing else
        binding.btnAnimSignUp.visibility = View.VISIBLE
        anim_btn.visibility = View.INVISIBLE
        anim_btn.pauseAnimation()
    }

}