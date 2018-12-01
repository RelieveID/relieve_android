package com.relieve.android.rsux.helper

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences


class PreferencesHelper(val context: Context) {
    companion object {
        private const val PREFERENCE_NAME = "RelieveIdPreference"
    }
    val preferences : SharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)
}