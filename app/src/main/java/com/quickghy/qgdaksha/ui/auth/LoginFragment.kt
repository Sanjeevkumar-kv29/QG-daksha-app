package com.quickghy.qgdaksha.ui.auth

import android.content.Intent
import android.hardware.input.InputManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieAnimationView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.quickghy.qgdaksha.R
import com.quickghy.qgdaksha.databinding.FragmentLoginBinding
import com.quickghy.qgdaksha.ui.dash.DashActivity
import com.quickghy.qgdaksha.util.toast
import org.koin.android.ext.android.inject
import org.koin.java.KoinJavaComponent.inject


class LoginFragment : Fragment(), AuthStateListener.LoginStateListener {

    lateinit var binding: FragmentLoginBinding
    lateinit var anim_btn: LottieAnimationView

    val viewModel: AuthViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )


        viewModel.loginStateListener = this
        binding.viewmodel = viewModel

        anim_btn = binding.btnAnimWheel
        viewModel.loginStateListener = this

//        binding.btnForgotPass.setOnClickListener { view ->
//            viewModel.doSendOTP()
//        }
//        binding.btnGoToSignup.setOnClickListener { view ->
//            viewModel.doSendOTP()
//            view.findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
//        }



        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        var gso: GoogleSignInOptions? =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        val mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso!!)

        binding.signInGoogle.setOnClickListener {

            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, 123)

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == 123) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)

            context?.toast("login successfully"+account?.displayName)

        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("singinresult", "signInResult:failed code=" + e.statusCode)
            context?.toast("Something Went Wrong")
        }
    }

    override fun onLoginStarted() {
        // put api call for login here
        context?.toast("login start...")
        anim_btn.visibility = View.VISIBLE
        binding.btnAnim.visibility = View.GONE
        anim_btn.playAnimation()
    }


    override fun onLoginSuccess(successRes: String) {
        context?.toast(successRes)
        anim_btn.visibility = View.GONE
        binding.btnAnim.visibility = View.VISIBLE
        anim_btn.pauseAnimation()
        startActivity(Intent(context,DashActivity::class.java))
        requireActivity().finish()
    }

    override fun onLoginFailure(message: String) {
        // display failure message toast
        context?.toast(message)
        binding.btnAnim.visibility = View.VISIBLE
        anim_btn.visibility = View.GONE
        anim_btn.pauseAnimation()
    }

    override fun onLoginNetworkFailure(message: String) {
        context?.toast(message)
    }
}