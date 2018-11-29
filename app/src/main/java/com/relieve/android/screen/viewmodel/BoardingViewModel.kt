package com.relieve.android.screen.viewmodel

import com.relieve.android.network.data.relieve.GoogleData
import com.relieve.android.network.service.RelieveService
import com.relieve.android.network.data.relieve.Login
import com.relieve.android.network.data.relieve.UserData
import com.relieve.android.network.data.relieve.UserToken
import com.relieve.android.network.isRequestSuccess
import com.relieve.android.rsux.framework.RsuxState
import com.relieve.android.rsux.framework.RsuxViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BoardingViewModel : RsuxViewModel<BoardingViewModel.BoardingState>() {
    class BoardingState : RsuxState

    override val state = BoardingState()
    private val relieveService = RelieveService.create()

    fun loginClick(username: String, password: String, onResponse: (Boolean, UserToken?) -> Unit) {
        relieveService.login(Login(password = password, username = username))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> onResponse(result.status?.isRequestSuccess() == true, result.content) },
                { onResponse(false, null) }
            ).also { compositeDisposable.add(it) }
    }

    fun registerClick(userData: UserData, onResponse: (Boolean, UserToken?) -> Unit) {
        relieveService.register(userData)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> onResponse(result.status?.isRequestSuccess() == true, result.content) },
                { error -> onResponse(false, null) }
            ).also { compositeDisposable.add(it) }
    }

    fun forgotPassClick(email: String, onResponse: (Boolean, UserToken?) -> Unit) {
        onResponse(false, null)
    }

    fun onGoogleLogin(idToken: String,
                      fullName: String,
                      onResponse: (Boolean, UserToken?) -> Unit) {
        val googleData = GoogleData(idToken, UserData(fullname = fullName))
        relieveService.googleLogin(googleData)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> onResponse(result.status?.isRequestSuccess() == true, result.content) },
                { error -> onResponse(false, null) }
            ).also { compositeDisposable.add(it) }
    }
}