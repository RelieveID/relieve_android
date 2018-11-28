package com.relieve.android.helper

import android.content.Context
import android.content.Context.MODE_PRIVATE


class PreferencesHelper(val context: Context){
    companion object {
        private const val PREFERENCE_NAME = "RelieveIdPreference"

        private const val HAS_SEEN_WALKTHROUGH = "data.source.prefs.HAS_SEEN_WALKTHROUGH"
    }
    private val preferences = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)

    // save device token
    var hasSeenWalkthrough = preferences.getBoolean(HAS_SEEN_WALKTHROUGH, false)
        set(value) = preferences.edit().putBoolean(HAS_SEEN_WALKTHROUGH,value).apply()
}