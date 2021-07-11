package com.quickghy.qgdaksha.ui.dash
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.quickghy.qgdaksha.R
import android.view.View
import kotlinx.android.synthetic.main.activity_dash.*

class DashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash)

    }

    fun homeclick(view: View)
    {
        homeBtn.setColorFilter(Color.parseColor("#FFA200"))
        profileBTN.setColorFilter(Color.parseColor("#292932"))
        offersBTN.setColorFilter(Color.parseColor("#292932"))
        cartBTN.setColorFilter(Color.parseColor("#292932"))

        tvhome.setTextColor(Color.parseColor("#FFA200"))
        tvprofile.setTextColor(Color.parseColor("#292932"))
        tvoffers.setTextColor(Color.parseColor("#292932"))
        tvcart.setTextColor(Color.parseColor("#292932"))
    }
    fun profileclick(view: View){

        homeBtn.setColorFilter(Color.parseColor("#292932"))
        profileBTN.setColorFilter(Color.parseColor("#FFA200"))
        offersBTN.setColorFilter(Color.parseColor("#292932"))
        cartBTN.setColorFilter(Color.parseColor("#292932"))

        tvhome.setTextColor(Color.parseColor("#292932"))
        tvprofile.setTextColor(Color.parseColor("#FFA200"))
        tvoffers.setTextColor(Color.parseColor("#292932"))
        tvcart.setTextColor(Color.parseColor("#292932"))

    }

    fun offerclick(view: View){
        homeBtn.setColorFilter(Color.parseColor("#292932"))
        profileBTN.setColorFilter(Color.parseColor("#292932"))
        offersBTN.setColorFilter(Color.parseColor("#FFA200"))
        cartBTN.setColorFilter(Color.parseColor("#292932"))

        tvhome.setTextColor(Color.parseColor("#292932"))
        tvprofile.setTextColor(Color.parseColor("#292932"))
        tvoffers.setTextColor(Color.parseColor("#FFA200"))
        tvcart.setTextColor(Color.parseColor("#292932"))
    }

    fun cartclick(view: View){
        homeBtn.setColorFilter(Color.parseColor("#292932"))
        profileBTN.setColorFilter(Color.parseColor("#292932"))
        offersBTN.setColorFilter(Color.parseColor("#292932"))
        cartBTN.setColorFilter(Color.parseColor("#FFA200"))

        tvhome.setTextColor(Color.parseColor("#292932"))
        tvprofile.setTextColor(Color.parseColor("#292932"))
        tvoffers.setTextColor(Color.parseColor("#292932"))
        tvcart.setTextColor(Color.parseColor("#FFA200"))
    }

}