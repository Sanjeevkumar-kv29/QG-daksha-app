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
        // first parameter is the current edittext and second parameter is next binding.otpET
        binding.otpET1.addTextChangedListener(GenericTextWatcher(binding.otpET1, binding.otpET2))
        binding.otpET2.addTextChangedListener(GenericTextWatcher(binding.otpET2, binding.otpET3))
        binding.otpET3.addTextChangedListener(GenericTextWatcher(binding.otpET3, binding.otpET4))
        binding.otpET4.addTextChangedListener(GenericTextWatcher(binding.otpET4, binding.otpET5))
        binding.otpET5.addTextChangedListener(GenericTextWatcher(binding.otpET5, binding.otpET6))
        binding.otpET6.addTextChangedListener(GenericTextWatcher(binding.otpET6, null))

        //GenericKeyEvent here works for deleting the element and to switch back to previous binding.otpET
        // first parameter is the current binding.otpET and second parameter is previous binding.otpET
        binding.otpET1.setOnKeyListener(GenericKeyEvent(binding.otpET1, null))
        binding.otpET2.setOnKeyListener(GenericKeyEvent(binding.otpET2, binding.otpET1))
        binding.otpET3.setOnKeyListener(GenericKeyEvent(binding.otpET3, binding.otpET2))
        binding.otpET4.setOnKeyListener(GenericKeyEvent(binding.otpET4, binding.otpET3))
        binding.otpET4.setOnKeyListener(GenericKeyEvent(binding.otpET5, binding.otpET4))
        binding.otpET4.setOnKeyListener(GenericKeyEvent(binding.otpET6, binding.otpET5))

        return binding.root
    }

    override fun onSignUpOtpStarted() {
        TODO("Not yet implemented")
//        update ui or do other stuff
    }

    override fun onSignUpOtpSuccess(opt: String) {
        Toast.makeText(context, "$opt: sign up success!", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_verifyOtpFragment_to_loginFragment)
    }

    override fun onResetOtpFailure(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }


}

class GenericKeyEvent internal constructor(
    private val currentView: EditText,
    private val previousView: EditText?
) : View.OnKeyListener {


    override fun onKey(p0: View?, keyCode: Int, event: KeyEvent?): Boolean {
        if (event!!.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL && currentView.id != R.id.otpET1 && currentView.text.isEmpty()) {
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

    override fun afterTextChanged(editable: Editable) { // TODO Auto-generated method stub
        val text = editable.toString()
        when (currentView.id) {
            R.id.otpET1 -> if (text.length == 1) {
                nextView!!.requestFocus()
                otp.append(text)
            }
            R.id.otpET2 -> if (text.length == 1) {
                nextView!!.requestFocus()
                otp.append(text)
            }
            R.id.otpET3 -> if (text.length == 1) {
                nextView!!.requestFocus()
                otp.append(text)
            }
            R.id.otpET4 -> if (text.length == 1) {
                nextView!!.requestFocus()
                otp.append(text)
            }
            R.id.otpET5 -> if (text.length == 1) {
                nextView!!.requestFocus()
                otp.append(text)
            }
            R.id.otpET6 -> if (text.length == 1) {
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
    ) { // TODO Auto-generated method stub
    }

    override fun onTextChanged(
        arg0: CharSequence,
        arg1: Int,
        arg2: Int,
        arg3: Int
    ) { // TODO Auto-generated method stub
    }

}