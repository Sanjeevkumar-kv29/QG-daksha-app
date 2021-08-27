package com.quickghy.qgdaksha.ui.map

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.widget.Toast
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*
import com.google.maps.android.PolyUtil
import com.google.maps.android.PolyUtil.locationIndexOnEdgeOrPath
import com.quickghy.qgdaksha.R
import com.quickghy.qgdaksha.data.map.network.DirectionsApi
import com.quickghy.qgdaksha.data.map.network.response.Bounds
import com.quickghy.qgdaksha.data.map.network.response.DirectionResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.util.*

/**
 * @Author: Shubham Rimjha
 * @Date: 21-08-2021
 */

class MapAdapter(private val context: Context, private val map: GoogleMap, private val selfLoc: LatLng) {

    private val TAG: String = MapAdapter::class.java.name
    private lateinit var orderLatLng: LatLng

    private var points: MutableList<LatLng>? = null
    private lateinit var response: Response<DirectionResponse>


    /** This function is called from the callback class, everytime a updated coordinate is received from the Pubnub
     * Subscriber Object.
     */
    fun locationUpdated(newLocation: LinkedHashMap<*, *>?) {
        if (newLocation?.containsKey("lat") == true && newLocation.containsKey("lng")) {

            orderLatLng = LatLng(newLocation["lat"].toString().toDouble(), newLocation["lng"].toString().toDouble())
            updateRoute(orderLatLng)
            Log.d(TAG, "updateRoute called for - $newLocation")

        } else Log.w(TAG, "message ignored: $newLocation")
    }

    /** This function is called from the locationUpdate method to update the route shown on map.
     *  It checks if the route is present, if not it makes a api call to the Google Directions API.
     */
    private fun updateRoute(newLoc: LatLng) {
        if (points == null) {
            CoroutineScope(Dispatchers.IO).launch {
//---------------------------------RETROFIT Segment-------------------------------------
                response = DirectionsApi.invoke().getRoute(
                    "${selfLoc.latitude},${selfLoc.longitude}",
                    "${newLoc.latitude},${newLoc.longitude}",
                    "motorcycle",
                    ""
                )

                if (response.isSuccessful) {
                    val obj = response.body()
                    val bounds = obj!!.routes[0].bounds

                    updateBounds(bounds)
                    val encodedPolyline = obj.routes[0].overview_polyline.points
                    points = PolyUtil.decode(encodedPolyline)

                    plotLine(points!!)
                }
            }
        } else {
            val closestIndex = locationIndexOnEdgeOrPath(
                newLoc,
                points,
                false,
                false,
                10.0
            )

            if (closestIndex == -1) {
                points = null
                updateRoute(newLoc)
            } else {
                points = points!!.subList(0, closestIndex)
                plotLine(points!!)
            }
        }
    }

    private fun updateBounds(bounds: Bounds) {
        CoroutineScope(Dispatchers.Main).launch {
            val ne = LatLng(bounds.northeast.lat, bounds.northeast.lng)
            val sw = LatLng(bounds.southwest.lat, bounds.southwest.lng)
            map.setLatLngBoundsForCameraTarget(LatLngBounds(sw, ne))

        }
    }

    private fun plotLine(points: MutableList<LatLng>) {
        CoroutineScope(Dispatchers.Main).launch {
            if (points.isEmpty()) Toast.makeText(context, "Order Reached", Toast.LENGTH_LONG).show()
            else {
                val polylineOptions = PolylineOptions().addAll(points).color(Color.YELLOW).width(10f)
                map.addPolyline(polylineOptions)
                map.addMarker(
                    MarkerOptions().position(points.last())
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_marker_service))
                        .title("Your  Order")
                )
            }
        }
    }
}