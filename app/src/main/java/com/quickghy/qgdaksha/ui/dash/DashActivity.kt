package com.quickghy.qgdaksha.ui.dash

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.commit
import com.quickghy.qgdaksha.R
import com.quickghy.qgdaksha.ui.auth.AuthMainActivity
import com.quickghy.qgdaksha.ui.dash.cart.CartFragment
import com.quickghy.qgdaksha.ui.dash.home.HomeFragment
import com.quickghy.qgdaksha.ui.dash.home.HomeViewModel
import com.quickghy.qgdaksha.ui.dash.offers.OffersFragment
import com.quickghy.qgdaksha.ui.dash.profile.MapMainActivity
import com.quickghy.qgdaksha.ui.dash.profile.ProfileFragment
import kotlinx.android.synthetic.main.activity_dash.*
import org.koin.android.ext.android.inject

class DashActivity : AppCompatActivity() {

    lateinit var fragmentContainer: FragmentContainerView
    val homeFragment = HomeFragment.newInstance()
    val fragmentManager = supportFragmentManager

    val viewModel: DashViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash)

        fragmentContainer = findViewById(R.id.dash_host_fragment)
        supportFragmentManager.beginTransaction().add(R.id.dash_host_fragment, homeFragment)
            .commit()
        supportFragmentManager.commit {
            add(R.id.dash_host_fragment, HomeFragment.newInstance())
        }
    }


    fun homeclick(view: View) {

        fragmentManager.commit {
            replace(R.id.dash_host_fragment, HomeFragment.newInstance())
        }


        homeBtn.setColorFilter(Color.parseColor("#FFA200"))
        profileBTN.setColorFilter(Color.parseColor("#292932"))
        offersBTN.setColorFilter(Color.parseColor("#292932"))
        cartBTN.setColorFilter(Color.parseColor("#292932"))

        tvhome.setTextColor(Color.parseColor("#FFA200"))
        tvprofile.setTextColor(Color.parseColor("#292932"))
        tvoffers.setTextColor(Color.parseColor("#292932"))
        tvcart.setTextColor(Color.parseColor("#292932"))
    }

    fun profileclick(view: View) {
        fragmentManager.commit {
            replace(R.id.dash_host_fragment, ProfileFragment.newInstance())
        }
        homeBtn.setColorFilter(Color.parseColor("#292932"))
        profileBTN.setColorFilter(Color.parseColor("#FFA200"))
        offersBTN.setColorFilter(Color.parseColor("#292932"))
        cartBTN.setColorFilter(Color.parseColor("#292932"))

        tvhome.setTextColor(Color.parseColor("#292932"))
        tvprofile.setTextColor(Color.parseColor("#FFA200"))
        tvoffers.setTextColor(Color.parseColor("#292932"))
        tvcart.setTextColor(Color.parseColor("#292932"))

    }

    fun offerclick(view: View) {
        fragmentManager.commit {
            replace(R.id.dash_host_fragment, OffersFragment.newInstance())
        }
        homeBtn.setColorFilter(Color.parseColor("#292932"))
        profileBTN.setColorFilter(Color.parseColor("#292932"))
        offersBTN.setColorFilter(Color.parseColor("#FFA200"))
        cartBTN.setColorFilter(Color.parseColor("#292932"))

        tvhome.setTextColor(Color.parseColor("#292932"))
        tvprofile.setTextColor(Color.parseColor("#292932"))
        tvoffers.setTextColor(Color.parseColor("#FFA200"))
        tvcart.setTextColor(Color.parseColor("#292932"))
    }

    fun cartclick(view: View) {
        fragmentManager.commit {
            replace(R.id.dash_host_fragment, CartFragment.newInstance())
        }
        homeBtn.setColorFilter(Color.parseColor("#292932"))
        profileBTN.setColorFilter(Color.parseColor("#292932"))
        offersBTN.setColorFilter(Color.parseColor("#292932"))
        cartBTN.setColorFilter(Color.parseColor("#FFA200"))

        tvhome.setTextColor(Color.parseColor("#292932"))
        tvprofile.setTextColor(Color.parseColor("#292932"))
        tvoffers.setTextColor(Color.parseColor("#292932"))
        tvcart.setTextColor(Color.parseColor("#FFA200"))
    }

    fun mapclick(view: View)
    {
        startActivity(Intent(this, AuthMainActivity::class.java))
    }


}