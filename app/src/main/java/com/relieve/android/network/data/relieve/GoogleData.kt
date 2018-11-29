package com.relieve.android.network.data.relieve

import com.google.gson.annotations.SerializedName

data class GoogleData(

	@field:SerializedName("idToken")
	val idToken: String? = null,

	@field:SerializedName("profile")
	val profile: UserData? = null

)
