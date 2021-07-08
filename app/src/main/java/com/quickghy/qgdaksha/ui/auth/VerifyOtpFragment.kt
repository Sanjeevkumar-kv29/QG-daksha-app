package com.quickghy.qgdaksha.ui.auth

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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


var otp: StringBuilder = StringBuilder()
lateinit var viewModel: AuthViewModel

class VerifyOtpFragment : Fragment(), AuthStateListener.SignUpOtpStateListener {

    lateinit var binding: FragmentVerifyOtpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // val view = inflater.inflate(R.layout.fragment_verify_otp, container, false)
        activity.let {
            viewModel = ViewModelProvider(it!!).get(AuthViewModel::class.java)
        }

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_verify_otp,
            container,
            false
        )
        binding.viewmodel = viewModel

        //GenericTextWatcher here works only for moving to next edittext when a number is entered
        // first paramEter is the current edittext and second paramEter is next binding.otpEt
        binding.otpEt1.addTextChangedListener(GenericTextWatcher(binding.otpEt1, binding.otpEt2))
        binding.otpEt2.addTextChangedListener(GenericTextWatcher(binding.otpEt2, binding.otpEt3))
        binding.otpEt3.addTextChangedListener(GenericTextWatcher(binding.otpEt3, binding.otpEt4))
        binding.otpEt4.addTextChangedListener(GenericTextWatcher(binding.otpEt4, binding.otpEt5))
        binding.otpEt5.addTextChangedListener(GenericTextWatcher(binding.otpEt5, binding.otpEt6))
        binding.otpEt6.addTextChangedListener(GenericTextWatcher(binding.otpEt6, null))

        //GenericKeyEvent here works for delEting the element and to switch back to previous binding.otpEt
        // first paramEter is the current binding.otpEt and second paramEter is previous binding.otpEt
        binding.otpEt1.setOnKeyListener(GenericKeyEvent(binding.otpEt1, null))
        binding.otpEt2.setOnKeyListener(GenericKeyEvent(binding.otpEt2, binding.otpEt1))
        binding.otpEt3.setOnKeyListener(GenericKeyEvent(binding.otpEt3, binding.otpEt2))
        binding.otpEt4.setOnKeyListener(GenericKeyEvent(binding.otpEt4, binding.otpEt3))
        binding.otpEt4.setOnKeyListener(GenericKeyEvent(binding.otpEt5, binding.otpEt4))
        binding.otpEt4.setOnKeyListener(GenericKeyEvent(binding.otpEt6, binding.otpEt5))

        return binding.root
    }

    override fun onSignUpOtpStarted() {
//        update ui or do other stuff
        binding.Resend.isClickable = false
    }

    override fun onSignUpOtpSuccess(opt: String) {
        Toast.makeText(context, "$opt: Password reset successfully!", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_verifyOtpFragment_to_loginFragment)
    }

    override fun onResetOtpFailure(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        binding.Resend.isClickable = false
    }


}

class GenericKeyEvent internal constructor(
    private val currentView: EditText,
    private val previousView: EditText?
) : View.OnKeyListener {


    override fun onKey(p0: View?, keyCode: Int, event: KeyEvent?): Boolean {
        if (event!!.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL && currentView.id != R.id.otp_et1 && currentView.text.isEmpty()) {
            //If current is empty then previous EditText's number will also be deleted
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
    private val nextView: View?
) : TextWatcher {

    override fun afterTextChanged(editable: Editable) { // TODO Auto-generated mEthod stub
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
                nextView!!.requestFocus()
                otp.append(text)
            }
            R.id.otp_et5 -> if (text.length == 1) {
                nextView!!.requestFocus()
                otp.append(text)
            }
            R.id.otp_et6 -> if (text.length == 1) {
                hideKeyboard(currentView)
                otp.append(text)
                if (otp.length == 6) {
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