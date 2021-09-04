package com.quickghy.qgdaksha.ui.map

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.maps.SupportMapFragment
import com.quickghy.qgdaksha.R
import org.koin.android.ext.android.inject

class MapsActivity : FragmentActivity() {

    val viewModel: MapViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(viewModel)
    }
}
