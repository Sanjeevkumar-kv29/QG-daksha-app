package com.quickghy.qgdaksha.ui.auth

import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.quickghy.qgdaksha.R
import com.quickghy.qgdaksha.databinding.FragmentVerifyOtpBinding
import com.quickghy.qgdaksha.util.toast
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


var otp: StringBuilder = StringBuilder()
class VerifyOtpFragment : Fragment(), AuthStateListener.LoginStateListener {

    lateinit var binding: FragmentVerifyOtpBinding

    private val viewModel by sharedViewModel<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_verify_otp,
            container,
            false
        )


        binding.viewmodel = viewModel

        val timer = object: CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.Resend.text = "Re-send OTP? (${millisUntilFinished/1000})"
            }

            override fun onFinish() {
                binding.Resend.isEnabled = true
            }
        }


        binding.btnSendVCode.setOnClickListener {
            timer.start()
        }

        binding.otpEt1.addTextChangedListener(GenericTextWatcher(binding.otpEt1, binding.otpEt2,viewModel))
        binding.otpEt2.addTextChangedListener(GenericTextWatcher(binding.otpEt2, binding.otpEt3,viewModel))
        binding.otpEt3.addTextChangedListener(GenericTextWatcher(binding.otpEt3, binding.otpEt4,viewModel))
        binding.otpEt4.addTextChangedListener(GenericTextWatcher(binding.otpEt4, null,viewModel))

        //GenericKeyEvent here works for delEting the element and to switch back to previous binding.otpEt
        // first paramEter is the current binding.otpEt and second paramEter is previous binding.otpEt

        binding.otpEt1.setOnKeyListener(GenericKeyEvent(binding.otpEt1, null))
        binding.otpEt2.setOnKeyListener(GenericKeyEvent(binding.otpEt2, binding.otpEt1))
        binding.otpEt3.setOnKeyListener(GenericKeyEvent(binding.otpEt3, binding.otpEt2))
        binding.otpEt4.setOnKeyListener(GenericKeyEvent(binding.otpEt4, binding.otpEt3))

        return binding.root
    }

    override fun onLoginStarted() {
        binding.Resend.isEnabled = false
        context?.toast("Verifying OTP")
    }

    override fun onLoginSuccess(opt: String) {
        context?.toast("Login Successfull")
        findNavController().navigate(R.id.action_verifyOtpFragment_to_loginFragment)
    }

    override fun onLoginFailure(message: String) {
        context?.toast("Some Thing Went Wrong")
        binding.Resend.isEnabled = true
    }

    override fun onLoginNetworkFailure(message: String) {
        TODO("Not yet implemented")
    }



class GenericKeyEvent internal constructor(
    private val currentView: EditText,
    private val previousView: EditText?
) : View.OnKeyListener {

    override fun onKey(p0: View?, keyCode: Int, event: KeyEvent?): Boolean {
        if (event!!.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL && currentView.id != R.id.otp_et1 && currentView.text.isEmpty()) {
            previousView!!.text = null
            otp.deleteAt(otp.lastIndex)
            previousView.requestFocus()
            return true
        }
        return false
    }


}

class GenericTextWatcher internal constructor(
    private val currentView: View,
    private val nextView: View?,
    val viewModel: AuthViewModel
) : TextWatcher {

    override fun afterTextChanged(editable: Editable) {
        val text = editable.toString()
        when (currentView.id) {
                R.id.otp_et1 -> if (text.length == 1) {
                    nextView!!.requestFocus()
                    otp.append(text)
                }
                R.id.otp_et2 -> if (text.length == 1) {
                    nextView!!.requestFocus()
                    otp.append(text)
                }
                R.id.otp_et3 -> if (text.length == 1) {
                    nextView!!.requestFocus()
                    otp.append(text)
                }
                R.id.otp_et4 -> if (text.length == 1) {
                    hideKeyboard(currentView)
                    otp.append(text)

                    if (otp.length == 4 ){
                        viewModel.otp = otp.toString()
                    }
                }
            //You can use EditText6 same as above to hide the keyboard
        }
    }


    private fun hideKeyboard(view: View) {
        val imm = getSystemService(view.context, InputMethodManager::class.java)
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun beforeTextChanged(
        arg0: CharSequence,
        arg1: Int,
        arg2: Int,
        arg3: Int
    ) {
    }

    override fun onTextChanged(
        arg0: CharSequence,
        arg1: Int,
        arg2: Int,
        arg3: Int
    ) {
    }

}

}

