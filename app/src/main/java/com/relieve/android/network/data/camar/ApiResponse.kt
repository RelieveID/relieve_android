package com.relieve.android.network.data.camar

import com.google.gson.annotations.SerializedName

data class ApiResponse<T> (
        @field:SerializedName("status")
        val status: Int? = null,
        @field:SerializedName("data")
        val data: T? = null
)