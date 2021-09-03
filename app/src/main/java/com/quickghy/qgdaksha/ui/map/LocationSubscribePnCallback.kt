package com.quickghy.qgdaksha.ui.map

import android.util.Log
import com.pubnub.api.PubNub
import com.pubnub.api.callbacks.SubscribeCallback
import com.pubnub.api.models.consumer.PNStatus
import com.pubnub.api.models.consumer.pubsub.PNMessageResult
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult
import com.pubnub.api.models.consumer.pubsub.PNSignalResult
import com.quickghy.qgdaksha.util.map_util.JsonUtil
import java.util.*

/**
 * @Author: Shubham Rimjha
 * @Date: 25-07-2021
 */

class LocationSubscribePnCallback(
    private val mapAdapter: MapAdapter,
    private val watchChannel: String
) :
    SubscribeCallback() {

    init {
        Log.d(TAG, "LocationSubscribePnCallback initialized")
    }

    override fun status(pubnub: PubNub, pnStatus: PNStatus) {
        println("Status category: ${pnStatus.category}")
        // PNConnectedCategory, PNReconnectedCategory, PNDisconnectedCategory
        println("Status operation: ${pnStatus.operation}")
        // PNSubscribeOperation, PNHeartbeatOperation
        println("Status error: ${pnStatus.error}")
        // true or false
    }

    override fun message(pubnub: PubNub, pnMessageResult: PNMessageResult) {
        if (pnMessageResult.channel != watchChannel) {
            Log.d(TAG, "channel mismatch: $watchChannel, ${pnMessageResult.channel}")
            return
        }
        try {
            Log.d(TAG, "message: $pnMessageResult")
            val newLocation: LinkedHashMap<*, *>? = JsonUtil.fromJson(
                pnMessageResult.message.toString(),
                LinkedHashMap::class.java
            )
            mapAdapter.locationUpdated(newLocation)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    override fun presence(pubnub: PubNub, pnPresenceEventResult: PNPresenceEventResult) {
        if (pnPresenceEventResult.channel != watchChannel) {
            return
        }
    }

    override fun signal(pubnub: PubNub, pnSignalResult: PNSignalResult) {
        super.signal(pubnub, pnSignalResult)
        Log.d(TAG, "presence: $pnSignalResult")

    }

    companion object {
        private val TAG = LocationSubscribePnCallback::class.java.name
    }

}
