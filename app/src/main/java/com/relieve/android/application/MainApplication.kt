package com.relieve.android.application

import androidx.multidex.MultiDexApplication
import com.facebook.stetho.Stetho
import com.google.firebase.iid.FirebaseInstanceId
import com.relieve.android.helper.tokenFCM
import com.relieve.android.rsux.helper.PreferencesHelper

class MainApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)

        // only request
        if (PreferencesHelper(applicationContext).tokenFCM.isNullOrEmpty()) {
            generateToken()

        }
    }

    private fun generateToken() {
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener {
            if (it.isSuccessful) {
                PreferencesHelper(applicationContext).tokenFCM = it.result?.token
            } else {
                // try regenerate until success
                // this is thread safe, because depend on task
                generateToken()
            }
            }.addOnFailureListener {
                // try regenerate until success
                // this is thread safe, because depend on task
                generateToken()
            }
    }
}