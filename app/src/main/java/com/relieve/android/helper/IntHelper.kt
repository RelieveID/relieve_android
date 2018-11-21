package com.relieve.android.helper

import android.content.res.Resources

fun Int.dptoPx() = (this * Resources.getSystem().displayMetrics.density).toInt()