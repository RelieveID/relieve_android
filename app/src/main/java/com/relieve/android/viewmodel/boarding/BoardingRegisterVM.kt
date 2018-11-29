package com.relieve.android.viewmodel.boarding

import androidx.lifecycle.ViewModel
import com.relieve.android.network.RelieveService
import com.relieve.android.network.data.relieve.Register
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class BoardingRegisterVM : ViewModel() {

    private val relieveService by lazy {
        RelieveService.create()
    }

    fun registerClick(userData: Register, onResponse: (Boolean) -> Unit) {
        relieveService.register(userData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> onResponse(true) },
                        { error -> onResponse(false) }
                )

        onResponse(true)
    }
}