package com.quickghy.qgdaksha.ui.auth

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.quickghy.qgdaksha.R
import com.quickghy.qgdaksha.databinding.FragmentVerifyOtpBinding

class VerifyOtpFragment : Fragment(), AuthStateListener.SignUpOtpStateListener {

    lateinit var viewModel: AuthViewModel
    lateinit var binding: FragmentVerifyOtpBinding
    lateinit var otp_et1: EditText
    lateinit var otp_et2: EditText
    lateinit var otp_et3: EditText
    lateinit var otp_et4: EditText
    lateinit var otp_et5: EditText
    lateinit var otp_et6: EditText
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_verify_otp, container, false)

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
        // first parameter is the current edittext and second parameter is next otp_et
        otp_et1.addTextChangedListener(GenericTextWatcher(otp_et1, otp_et2))
        otp_et2.addTextChangedListener(GenericTextWatcher(otp_et2, otp_et3))
        otp_et3.addTextChangedListener(GenericTextWatcher(otp_et3, otp_et4))
        otp_et4.addTextChangedListener(GenericTextWatcher(otp_et4, otp_et5))
        otp_et5.addTextChangedListener(GenericTextWatcher(otp_et5, otp_et6))
        otp_et6.addTextChangedListener(GenericTextWatcher(otp_et6, null))

        //GenericKeyEvent here works for deleting the element and to switch back to previous otp_et
        // first parameter is the current otp_et and second parameter is previous otp_et
        otp_et1.setOnKeyListener(GenericKeyEvent(otp_et1, null))
        otp_et2.setOnKeyListener(GenericKeyEvent(otp_et2, otp_et1))
        otp_et3.setOnKeyListener(GenericKeyEvent(otp_et3, otp_et2))
        otp_et4.setOnKeyListener(GenericKeyEvent(otp_et4, otp_et3))
        otp_et4.setOnKeyListener(GenericKeyEvent(otp_et5, otp_et4))
        otp_et4.setOnKeyListener(GenericKeyEvent(otp_et6, otp_et5))

        return binding.root
    }

    override fun onSignUpOtpStarted() {
        TODO("Not yet implemented")
//        update ui or do other stuff
    }

    override fun onSignUpOtpSuccess(opt: String) {
        Toast.makeText(context, opt + ": sign up success!", Toast.LENGTH_SHORT).show()
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
        if (event!!.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL && currentView.id != R.id.otp_et1 && currentView.text.isEmpty()) {
            //If current is empty then previous EditText's number will also be deleted
            previousView!!.text = null
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
            R.id.otp_et1 -> if (text.length == 1) nextView!!.requestFocus()
            R.id.otp_et2 -> if (text.length == 1) nextView!!.requestFocus()
            R.id.otp_et3 -> if (text.length == 1) nextView!!.requestFocus()
            R.id.otp_et4 -> if (text.length == 1) nextView!!.requestFocus()
            R.id.otp_et5 -> if (text.length == 1) nextView!!.requestFocus()
            R.id.otp_et6 -> if (text.length == 1) nextView!!.requestFocus()
            //You can use EditText4 same as above to hide the keyboard
        }
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