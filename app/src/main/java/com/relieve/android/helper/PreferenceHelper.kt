package com.relieve.android.helper

import android.content.Context
import android.content.Context.MODE_PRIVATE


class PreferencesHelper(val context: Context){
    companion object {
        private const val PREFERENCE_NAME = "RelieveIdPreference"

        private const val HAS_SEEN_WALKTHROUGH = "data.source.prefs.HAS_SEEN_WALKTHROUGH"
        private const val IS_SIGNED_IN = "data.source.prefs.IS_SIGNED_IN"
        private const val TOKEN = "data.source.prefs.TOKEN"
    }
    private val preferences = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)

    var token = preferences.getString(TOKEN, "")
        set(value) = preferences.edit().putString(TOKEN, value).apply()

    var hasSeenWalkthrough = preferences.getBoolean(HAS_SEEN_WALKTHROUGH, false)
        set(value) = preferences.edit().putBoolean(HAS_SEEN_WALKTHROUGH,value).apply()

    var isSignedIn = preferences.getBoolean(IS_SIGNED_IN, false)
        set(value) = preferences.edit().putBoolean(IS_SIGNED_IN,value).apply()
}