package com.relieve.android.viewmodel.boarding

import androidx.lifecycle.ViewModel

class UserData (
    username: String,
    password: String,
    fullName: String,
    email: String,
    phoneNum: String,
    birthDate: String
)

class BoardingRegisterVM : ViewModel() {

    fun registerClick(userData: UserData, onResponse: (Boolean) -> Unit) {
        onResponse(true)
    }
}