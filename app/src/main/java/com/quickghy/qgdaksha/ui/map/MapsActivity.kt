package com.quickghy.qgdaksha.ui.map

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.pubnub.api.PNConfiguration
import com.pubnub.api.PubNub
import com.pubnub.api.enums.PNReconnectionPolicy
import com.quickghy.qgdaksha.R
import com.quickghy.qgdaksha.util.map_util.PNConstants
import java.util.*

class MapsActivity : FragmentActivity(), OnMapReadyCallback {

    private lateinit var pubnub: PubNub
    private lateinit var options: MarkerOptions

    private var mMap: GoogleMap? = null
    private var mMarkerPoints: ArrayList<LatLng>? = null

    private val selfPoint = LatLng(21.204794, 79.067607)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        val pnConfiguration = PNConfiguration().apply {
            subscribeKey = PNConstants.PUBNUB_SUBSCRIBE_KEY
            publishKey = PNConstants.PUBNUB_PUBLISH_KEY
            secure = true
            uuid = PNConstants.DEMO_USER
            reconnectionPolicy = PNReconnectionPolicy.LINEAR
            maximumReconnectionRetries = 5
        }

        pubnub = PubNub(pnConfiguration)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap

        pubnub.addListener(
            LocationSubscribePnCallback(
                MapAdapter(this, googleMap, selfPoint),
                PNConstants.SUBSCRIBE_CHANNEL_NAME
            )
        )
        pubnub.subscribe(
            channels = listOf(PNConstants.SUBSCRIBE_CHANNEL_NAME)
        )

        mMarkerPoints = ArrayList()
        //self point
        mMarkerPoints!!.add(selfPoint)
        options = MarkerOptions()
        options.position(selfPoint)
        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))

        mMap!!.animateCamera(
            CameraUpdateFactory.newLatLngZoom(
                mMarkerPoints!![0],
                14f
            )
        )


        mMap!!.setOnMapClickListener {
            mMap!!.clear()

            val markerOptions = MarkerOptions()
                .icon(
                    BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)
                )
                .position(it)

            mMap!!.addMarker(markerOptions)
            publishLoc(getNewLocationMessage(it))
        }
    }


    private fun getNewLocationMessage(location: LatLng): Map<String, String> {
        return mapOf(
            "who" to PNConstants.DEMO_USER,
            "lat" to location.latitude.toString(),
            "lng" to location.longitude.toString()
        )
    }

    private fun publishLoc(message: Map<String, String>) {
        pubnub.publish(
            channel = PNConstants.SUBSCRIBE_CHANNEL_NAME,
            message = message
        ).async { _, status ->
            if (status.error)
                Log.i("Publish", "Error: ${status.error}")
            else {
                Log.i("Publish", "Published Location - $message")
            }
        }
    }

}