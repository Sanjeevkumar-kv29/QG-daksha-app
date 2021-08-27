package com.quickghy.qgdaksha.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.quickghy.qgdaksha.R
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject

class AuthMainActivity : AppCompatActivity() {

    val viewModel: AuthViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }
}