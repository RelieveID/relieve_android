package com.relieve.android.network.data.relieve

import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("content")
	val content: T? = null,

	@field:SerializedName("status")
	val status: Int? = null
)