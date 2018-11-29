package com.relieve.android.network.data.relieve

import com.google.gson.annotations.SerializedName

data class Register(

        @field:SerializedName("username")
        val username: String? = null,

        @field:SerializedName("password")
        val password: String? = null,

        @field:SerializedName("fullname")
        val fullname: String? = null,

        @field:SerializedName("email")
        val email: String? = null,

        @field:SerializedName("phone")
        val phone: String? = null,

        @field:SerializedName("birthdate")
        val birthdate: String? = null


)