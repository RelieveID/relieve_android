package com.relieve.android.network.data.camar

import com.google.gson.annotations.SerializedName

data class Event(
    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("location")
    val location: Location? = null,

    @field:SerializedName("mag")
    val magnitude: Double? = null,

    @field:SerializedName("depth")
    val depth: Double? = null,

    @field:SerializedName("place")
    val place: String? = null,

    @field:SerializedName("time")
    val time: Long? = null
)