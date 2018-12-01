package com.relieve.android.application

import androidx.multidex.MultiDexApplication
import com.facebook.stetho.Stetho
import com.google.firebase.iid.FirebaseInstanceId
import com.relieve.android.helper.preference
import com.relieve.android.helper.tokenFCM

class MainApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)

        // only request
        if (applicationContext.preference.tokenFCM.isNullOrEmpty()) {
            generateToken()
        }
    }

    private fun generateToken() {
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener {
            if (it.isSuccessful) {
                applicationContext.preference.tokenFCM = it.result?.token
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