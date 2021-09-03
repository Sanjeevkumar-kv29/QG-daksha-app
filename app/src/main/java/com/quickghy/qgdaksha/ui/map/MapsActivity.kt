package com.quickghy.qgdaksha.ui.map

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.maps.SupportMapFragment
import com.quickghy.qgdaksha.R
import org.koin.android.ext.android.inject

class MapsActivity : FragmentActivity() {

    val viewModel: MapViewModel by inject()

    //   private lateinit var pubnub: PubNub
    //   private lateinit var options: MarkerOptions
    //  private var mMap: GoogleMap? = null
    //  private var mMarkerPoints: ArrayList<LatLng>? = null
    // private val selfPoint = LatLng(21.204794, 79.067607)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(viewModel)
    }
}
//    override fun onMapReady(googleMap: GoogleMap) {
//
//        mMap = googleMap
//
//        pubnub.addListener(
//            LocationSubscribePnCallback(
//                MapAdapter(this, googleMap, viewModel.mapRepo),
//                PNConstants.SUBSCRIBE_CHANNEL_NAME
//            )
//        )
//        pubnub.subscribe(
//            channels = listOf(PNConstants.SUBSCRIBE_CHANNEL_NAME)
//        )
//
//        mMarkerPoints = ArrayList()
//        //self point
//        mMarkerPoints!!.add(selfPoint)
//        options = MarkerOptions()
//        options.position(selfPoint)
//        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
//
//        mMap!!.animateCamera(
//            CameraUpdateFactory.newLatLngZoom(
//                mMarkerPoints!![0],
//                14f
//            )
//        )
//
//
//        mMap!!.setOnMapClickListener {
//            mMap!!.clear()
//
//            val markerOptions = MarkerOptions()
//                .icon(
//                    BitmapDescriptorFactory
//                        .defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)
//                )
//                .position(it)
//
//            mMap!!.addMarker(markerOptions)
//            publishLoc(getNewLocationMessage(it))
//        }
//    }
//    private fun getNewLocationMessage(location: LatLng): Map<String, String> {
//        return mapOf(
//            "who" to PNConstants.DEMO_USER,
//            "lat" to location.latitude.toString(),
//            "lng" to location.longitude.toString()
//        )
//    }
//
//    private fun publishLoc(message: Map<String, String>) {
//        pubnub.publish(
//            channel = PNConstants.SUBSCRIBE_CHANNEL_NAME,
//            message = message
//        ).async { _, status ->
//            if (status.error)
//                Log.i("Publish", "Error: ${status.error}")
//            else {
//                Log.i("Publish", "Published Location - $message")
//            }
//        }
//    }
