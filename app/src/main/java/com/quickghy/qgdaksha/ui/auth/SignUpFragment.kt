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
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment : Fragment(), AuthStateListener.SignUpStateListener {

    private val viewModel by sharedViewModel<AuthViewModel>()
    lateinit var binding: FragmentSignUpBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

//        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)


        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_sign_up,
            container,
            false
        )


        binding.viewmodel = viewModel

        viewModel.signUpStateListener = this


        return binding.root
    }

    override fun onSignUpStarted() {
        //put api to for
//        show progress bar here
        binding.btnAnimWheel.visibility = View.VISIBLE
        binding.btnAnim.visibility = View.GONE
    }

    override fun onSignUpSuccess(SignUpResponse: String) {
        Toast.makeText(context, SignUpResponse, Toast.LENGTH_SHORT).show()
        binding.btnAnimWheel.visibility = View.GONE
        binding.btnAnim.visibility = View.VISIBLE
//        do nav here to the otp screen
    }


    override fun onSignUpFailure(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
//        toast error and do nothing else
        binding.btnAnim.visibility = View.VISIBLE
        binding.btnAnimWheel.visibility = View.GONE
    }

}