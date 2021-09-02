package com.quickghy.qgdaksha

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.quickghy.qgdaksha.ui.auth.AuthMainActivity
import com.quickghy.qgdaksha.ui.dash.DashActivity
import java.util.*

class Activity_Splashscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen)

        Handler().postDelayed({
            val intent = Intent(this, DashActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)


    }
}