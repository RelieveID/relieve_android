package com.relieve.android.helper

import com.relieve.android.rsux.helper.PreferencesHelper


const val PREFERENCE_NAME = "RelieveIdPreference"

const val HAS_SEEN_WALKTHROUGH = "data.source.prefs.HAS_SEEN_WALKTHROUGH"
const val IS_SIGNED_IN = "data.source.prefs.IS_SIGNED_IN"
const val TOKEN = "data.source.prefs.TOKEN"
const val REFRESH_TOKEN = "data.source.prefs.REFRESH_TOKEN"
const val TOKEN_EXPIRE = "data.source.prefs.TOKEN_EXPIRE"
const val FCM_TOKEN = "data.source.prefs.FCM_TOKEN"


var PreferencesHelper.token : String?
    get() = preferences.getString(TOKEN, "")
    set(value) = preferences.edit().putString(TOKEN, value).apply()

var PreferencesHelper.tokenRefresh : String?
    get() = preferences.getString(REFRESH_TOKEN, "")
    set(value) = preferences.edit().putString(REFRESH_TOKEN, value).apply()

var PreferencesHelper.tokenExpire : Int
    get() = preferences.getInt(TOKEN_EXPIRE, 0)
    set(value) = preferences.edit().putInt(TOKEN_EXPIRE, value).apply()

var PreferencesHelper.tokenFCM : String?
    get() = preferences.getString(FCM_TOKEN, "")
    set(value) = preferences.edit().putString(FCM_TOKEN, value).apply()

var PreferencesHelper.hasSeenWalkthrough : Boolean
    get() = preferences.getBoolean(HAS_SEEN_WALKTHROUGH, false)
    set(value) = preferences.edit().putBoolean(HAS_SEEN_WALKTHROUGH,value).apply()

var PreferencesHelper.isSignedIn : Boolean
    get() = preferences.getBoolean(IS_SIGNED_IN, false)
    set(value) = preferences.edit().putBoolean(IS_SIGNED_IN,value).apply()