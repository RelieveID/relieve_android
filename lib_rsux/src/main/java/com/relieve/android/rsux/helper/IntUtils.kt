package com.relieve.android.rsux.helper

import android.content.res.Resources
import java.util.*

fun Int.dpToPx() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Long?.getTimeDiffInSecond() = this?.run {
    val netDate = Date(this * 1000)
    val curDate = Date()

    return@run (curDate.time - netDate.time) / 1000
} ?: 0