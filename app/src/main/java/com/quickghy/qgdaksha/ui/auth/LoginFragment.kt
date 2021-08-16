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
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.quickghy.qgdaksha.R
import com.quickghy.qgdaksha.databinding.FragmentLoginBinding
import com.quickghy.qgdaksha.ui.dash.DashActivity
import com.quickghy.qgdaksha.util.toast
import org.json.JSONObject
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.java.KoinJavaComponent.inject


class LoginFragment : Fragment(), AuthStateListener.LoginStateListener {

    lateinit var binding: FragmentLoginBinding
    lateinit var anim_btn: LottieAnimationView
    private val viewModel by sharedViewModel<AuthViewModel>()

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
                .requestIdToken("903207635478-d3h21sbg8bpusfvqldi01v2epek1qkui.apps.googleusercontent.com")
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
            val account = completedTask.getResult(
                ApiException::class.java
            )
            context?.toast("logining In as "+account?.email)
            val googleIdToken = account?.idToken?: ""
            Log.i("GToken", googleIdToken)
            viewModel.doGoogleLogin(googleIdToken)
            // Signed in successfully
            /*val googleId = account?.id ?: ""
            Log.i("Google ID",googleId)

            val googleFirstName = account?.givenName ?: ""
            Log.i("Google First Name", googleFirstName)

            val googleLastName = account?.familyName ?: ""
            Log.i("Google Last Name", googleLastName)

            val googleEmail = account?.email ?: ""
            Log.i("Google Email", googleEmail)

            val googleProfilePicURL = account?.photoUrl.toString()
            Log.i("Google Profile Pic URL", googleProfilePicURL)

            val googleIdToken = account?.idToken?: ""
            Log.i("Google ID Token", googleIdToken)

            val json = JSONObject()
            json.put("Google ID",googleId)
            json.put("Google Token ID",googleIdToken)
            json.put("Google First Name",googleFirstName)
            json.put("Google Last Name",googleLastName)
            json.put("Google Email",googleEmail)
            json.put("Google Profile URL",googleProfilePicURL)


            Log.i("RESPONSE",json.toString())*/

        } catch (e: ApiException) {
            // Sign in was unsuccessful
            context?.toast("Something Went Wrong")
            Log.e(
                "failed code=", e.statusCode.toString()
            )
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