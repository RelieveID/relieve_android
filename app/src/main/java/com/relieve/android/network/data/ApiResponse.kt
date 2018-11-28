package com.relieve.android.network.data

import com.google.gson.annotations.SerializedName

data class ApiResponse<T> (
        @field:SerializedName("status")
        val status: String? = null,
        @field:SerializedName("data")
        val data: T? = null
)