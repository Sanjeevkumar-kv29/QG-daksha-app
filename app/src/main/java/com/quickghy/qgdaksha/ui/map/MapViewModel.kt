package com.quickghy.qgdaksha.ui.map

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.pubnub.api.PNConfiguration
import com.pubnub.api.PubNub
import com.pubnub.api.enums.PNReconnectionPolicy
import com.quickghy.qgdaksha.util.map_util.PNConstants
import java.util.*

/**
 * @Author: Shubham Rimjha
 * @Date: 28-08-2021
 */
class MapViewModel(
    private val mapRepo: MapRepository,
    context: Context
) : ViewModel(), OnMapReadyCallback {

    val context = context.applicationContext

    private var pubnub: PubNub = PubNub(PNConfiguration().apply {
        subscribeKey = mapRepo.subKey
        secure = true
        uuid = mapRepo.uuid
        reconnectionPolicy = PNReconnectionPolicy.LINEAR
        maximumReconnectionRetries = 5
    })


    private var selfPoint: LatLng = mapRepo.selfPoint

    private lateinit var mMap: GoogleMap
    private lateinit var options: MarkerOptions

    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap

        pubnub.addListener(
            LocationSubscribePnCallback(
                MapAdapter(context, googleMap, mapRepo),
                mapRepo.subChannel
            )
        )
        pubnub.subscribe(
            channels = listOf(PNConstants.SUBSCRIBE_CHANNEL_NAME)
        )

        //self point

        options = MarkerOptions()
            .position(selfPoint)
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))

        mMap.addMarker(options)

        mMap.animateCamera(
            CameraUpdateFactory.newLatLngZoom(
                selfPoint,
                10f
            )
        )
    }
}

////        mMap.setOnMapClickListener {
////            mMap.clear()
////
////            val markerOptions = MarkerOptions()
////                .icon(
////                    BitmapDescriptorFactory
////                        .defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)
////                )
////                .position(it)
////
////            publishLoc(getNewLocationMessage(it))
////        }
