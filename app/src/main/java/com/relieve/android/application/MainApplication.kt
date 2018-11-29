package com.relieve.android.application

import androidx.multidex.MultiDexApplication
import com.facebook.stetho.Stetho

class MainApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}