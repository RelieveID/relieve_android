package com.relieve.android.viewmodel.boarding

import androidx.lifecycle.ViewModel
import com.relieve.android.network.service.RelieveService
import com.relieve.android.network.data.relieve.Login
import com.relieve.android.network.data.relieve.Register
import com.relieve.android.network.isRequestSuccess
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.disposables.CompositeDisposable



class BoardingViewModel : ViewModel() {

    val compositeDisposable = CompositeDisposable()

    private val relieveService by lazy {
        RelieveService.create()
    }

    fun loginClick(username: String, password: String, onResponse: (Boolean) -> Unit) {
        relieveService.login(Login(password = password, username = username))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> onResponse(result.status?.isRequestSuccess() == true) },
                { onResponse(false) }
            ).also { compositeDisposable.add(it) }
    }

    fun registerClick(userData: Register, onResponse: (Boolean) -> Unit) {
        relieveService.register(userData)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> onResponse(true) },
                { error -> onResponse(false) }
            ).also { compositeDisposable.add(it) }
    }

    fun onGoogleLogin(idToken: String, onResponse: (Boolean) -> Unit) {
        onResponse(true)
    }

    fun onDestroy () {
        compositeDisposable.dispose()
    }
}