package com.relieve.android.network.data.camar

import com.google.gson.annotations.SerializedName

data class Location(

	@field:SerializedName("coordinates")
	val coordinates: List<Double?>? = null,

	@field:SerializedName("type")
	val type: String? = null
)