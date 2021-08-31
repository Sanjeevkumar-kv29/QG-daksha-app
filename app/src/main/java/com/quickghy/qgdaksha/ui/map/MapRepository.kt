package com.quickghy.qgdaksha.ui.map

import com.google.android.gms.maps.model.LatLng
import com.quickghy.qgdaksha.data.map.network.DirectionsApi
import com.quickghy.qgdaksha.data.map.network.response.DirectionResponse
import com.quickghy.qgdaksha.util.map_util.PNConstants
import retrofit2.Response

/**
 * @Author: Shubham Rimjha
 * @Date: 28-08-2021
 */
class MapRepository(
    Dapi: DirectionsApi
) {
    //variables for pn channel object.
    var subChannel: String = PNConstants.SUBSCRIBE_CHANNEL_NAME
    var uuid: String = PNConstants.DEMO_USER
    var subKey: String = PNConstants.PUBNUB_SUBSCRIBE_KEY

    //variables for latlng.
    var selfPoint: LatLng = LatLng(21.204794, 79.067607)

    suspend fun getRoute(newLoc: LatLng, selfLoc: LatLng): Response<DirectionResponse> {
        val response = DirectionsApi.invoke().getRoute(
            "${selfLoc.latitude},${selfLoc.longitude}",
            "${newLoc.latitude},${newLoc.longitude}",
            "motorcycle",
            "AIzaSyB7rDvd5JtFzDtp5WFfKStc9V3RCPi2WVU"
        )
        return response
    }



}