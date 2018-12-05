package com.relieve.android.helper

fun String.getLocationsFromMMI() = this
    .removeSurrounding(" ") // remove leading and trailing ` `
    .replace(",", "") // remove `,`
    .split(" ") // split by ' '
    .mapIndexedNotNull { index, s ->
        if (index != 0 && index % 2 == 0) s else null
    }.joinToString(separator = ", ")