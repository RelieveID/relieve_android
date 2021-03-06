package com.relieve.android.network.data.camar

import com.google.gson.annotations.SerializedName

data class Event(
    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("location")
    val location: Location? = null,

    @field:SerializedName("time")
    val time: Long? = null,

    @field:SerializedName("event_type")
    val eventType: String? = null,

    @field:SerializedName("source")
    val source: String? = null,

    @field:SerializedName("event_detail")
    val eventDetail: Detail? = null

) {

    data class Detail (
        @field:SerializedName("depth")
        val depth: Double? = null,

        @field:SerializedName("mag")
        val magnitude: Double? = null,

        @field:SerializedName("mmi")
        val mmi: String? = null,

        @field:SerializedName("title")
        val title: String? = null
    )

}