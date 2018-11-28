package com.relieve.android.rsux.helper

fun secondToTimeText(seconds: Long) : String {
    return if (seconds == 0L) {
        "Live"
    } else {
        var remainingSecond = seconds
        val day = remainingSecond / 86400
        remainingSecond %= 86400

        val hour = remainingSecond / 3600
        remainingSecond %= 3600

        val minute = remainingSecond / 60
        remainingSecond %= 60

        val secondString = if (seconds > 0) "$remainingSecond detik" else ""
        val dayString = if (day > 0) "$day hari, " else ""
        val hourString = if (hour > 0) "$hour jam, " else ""
        val minuteString = if (minute > 0 && remainingSecond > 30) {
            "$minute menit, $secondString"
        } else if (minute > 0) {
            "$minute menit"
        } else {
            secondString
        }
        "$dayString$hourString$minuteString"
    }
}