package com.quickghy.qgdaksha.util.map_util

/**
 * @Author: Shubham Rimjha
 * @Date: 23-07-2021
 */
object PNConstants {
    const val PUBNUB_PUBLISH_KEY = "pub-c-d6f5f6de-7203-4c2f-8844-6120a2c969e4"
    const val PUBNUB_SUBSCRIBE_KEY = "sub-c-841b3ccc-e85e-11eb-b1e5-fa4b4b21bdc4"

    // The subscribe and publish channels should be made dynamic later depending on the order details.

    /**
     *  The publish channel is used by a message publisher, the service provider in this case to send location updates.
     */
    const val PUBLISH_CHANNEL_NAME = "location_channel"

    /**
     * The subscribe channel is used by a subscriber to receive messages. However messages are sent and received in a
     * generic format. So care should be taken to check the message intention (such as if it is a location message).
     */
    const val SUBSCRIBE_CHANNEL_NAME = "location_channel"

    /**
     * Another dynamic variable that should recognize a subscriber as well as a publisher over a channel.
     */
    const val DEMO_USER = "demo_user_101"
}
