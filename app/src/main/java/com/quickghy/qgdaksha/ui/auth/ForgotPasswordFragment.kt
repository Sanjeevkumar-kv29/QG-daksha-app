package com.quickghy.qgdaksha.ui.auth

import android.animation.Animator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.quickghy.qgdaksha.R
import com.quickghy.qgdaksha.databinding.FragmentForgotPasswordBinding
import com.quickghy.qgdaksha.util.toast
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ForgotPasswordFragment : Fragment(), AuthStateListener.ForgotPasswordStateListner {

    private val viewModel by sharedViewModel<AuthViewModel>()
    lateinit var binding: FragmentForgotPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_forgot_password, container, false)


        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_forgot_password,
            container,
            false
        )


        binding.viewmodel = viewModel
        viewModel.forgotPasswordStateListner = this


        binding.etPhone.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) ToggleImg(View.GONE)
            else ToggleImg(View.VISIBLE)
        }
        return binding.root
    }

    private fun ToggleImg(visible: Int) {
        if (visible == View.GONE) {
            binding.imgInForgotPass.animate().scaleY(0.0f).alpha(0.0f).translationZBy(100.0f)
                .setDuration(300)
                .setUpdateListener {
                    it.addListener(object : Animator.AnimatorListener {
                        override fun onAnimationEnd(animation: Animator?) {
                            binding.imgInForgotPass.visibility = visible
                        }

                        override fun onAnimationStart(animation: Animator?) {}
                        override fun onAnimationCancel(animation: Animator?) {}
                        override fun onAnimationRepeat(animation: Animator?) {}

                    })
                }
        }
    }

    override fun onSuccessForgot(opt: String) {
        context?.toast(opt)
        binding.btnSendVCode.text = "Sending OTP"
    }

    override fun onFailureForgot(message: String) {
        context?.toast(message)
        binding.btnSendVCode.text = "Continue"
    }

}