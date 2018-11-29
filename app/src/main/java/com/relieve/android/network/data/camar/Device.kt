package com.relieve.android.network.data

import com.google.gson.annotations.SerializedName

data class Device(
        @field:SerializedName("id")
        val id: String? = null,

        @field:SerializedName("device_id")
        val deviceId: String? = null,

        @field:SerializedName("location")
        val location: Location? = null,

        @field:SerializedName("token")
        val token: String? = null
)