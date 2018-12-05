package com.relieve.android.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

private const val BASE_URL = "https://api.relieve.id"
private const val SUCCESS_REQEUST_STATUS = 200
const val RETRY_SUM: Long = 5

fun Int?.isRequestSuccess () = this == SUCCESS_REQEUST_STATUS

const val PAGINATION_LIMIT = 10
const val PAGINATION_START = 1

object Camar {
    private const val PATH = "v1"

    const val URL = "$BASE_URL/$PATH/"
}

object Bakau {
    private const val PATH = "v2"
    const val URL = "$BASE_URL/$PATH/"

    const val HEADER_SECRET = "secret"
    const val SECRET = "YTvZ3kG9X9Vz6MLHdNIwnaTefjs2Udph"

    const val HEADER_AUTH = "Authorization"
}

fun hasNetwork(context: Context): Boolean? {
    var isConnected: Boolean? = false // Initial Value
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
    if (activeNetwork != null && activeNetwork.isConnected)
        isConnected = true
    return isConnected
}