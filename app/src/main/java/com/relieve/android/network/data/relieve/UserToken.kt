package com.relieve.android.network.data.relieve

import com.google.gson.annotations.SerializedName

data class UserToken(

	@field:SerializedName("refresh_token")
	val refreshToken: String? = null,

	@field:SerializedName("expires_in")
	val expiresIn: Int? = null,

	@field:SerializedName("token")
	val token: String? = null,

	@field:SerializedName("fcm_token")
	val fcmToken: String? = null

)