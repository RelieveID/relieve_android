package com.relieve.android.viewmodel.boarding

import androidx.lifecycle.ViewModel

class BoardingLoginVM : ViewModel() {
    fun loginClick(username: String, password: String, onResponse: (Boolean) -> Unit) {
        onResponse(true)
    }
}