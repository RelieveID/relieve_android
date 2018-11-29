package com.relieve.android.network.data.relieve

import com.google.gson.annotations.SerializedName

data class Login(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("fcm_token")
	val fcmToken: String? = null
)