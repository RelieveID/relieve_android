package com.relieve.android.screen.viewmodel

import androidx.lifecycle.MutableLiveData
import com.relieve.android.network.data.camar.Event
import com.relieve.android.network.data.relieve.UserData
import com.relieve.android.network.data.relieve.UserToken
import com.relieve.android.network.isRequestSuccess
import com.relieve.android.network.service.CamarService
import com.relieve.android.network.service.RelieveService
import com.relieve.android.rsux.framework.RsuxState
import com.relieve.android.rsux.framework.RsuxViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DashboardViewHolder : RsuxViewModel<DashboardViewHolder.DashboardState>() {
    class DashboardState : RsuxState {
        val earthQuakesLiveData = MutableLiveData<List<Event>>()
        val userLiveData = MutableLiveData<UserData>()
    }

    override val state = DashboardState()
    private val camarService = CamarService.create()
    private var relieveService: RelieveService? = null

    fun createRelieveService(authKey: String?) {
        if (relieveService == null) relieveService = RelieveService.create(authKey)
    }

    fun getUserProfile () {
        relieveService?.run {
            getProfile().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result ->
                        if (result.status.isRequestSuccess()) {
                            // this will trigger observer
                            state.userLiveData.value = result.content
                        } else {
                            discoverTopEvent()
                        }
                    },
                    { error -> getUserProfile() } // fail safe
                ).also { compositeDisposable.add(it) }
        }
    }

    fun discoverTopEvent() {
        camarService.getEarthQuakes(6, 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    if (result.status.isRequestSuccess()) {
                        // this will trigger observer
                        state.earthQuakesLiveData.value = result.data
                    } else {
                        discoverTopEvent()
                    }
                },
                { error -> discoverTopEvent() } // fail safe
            ).also { compositeDisposable.add(it) }
    }

    fun updateFcmToken(fcmToken: String) {
        relieveService?.run {
            updateFcmToken(UserToken(fcmToken = fcmToken))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result ->
                        if (!result.status.isRequestSuccess()) {
                            updateFcmToken(fcmToken)
                        }
                    },
                    { error -> updateFcmToken(fcmToken) } // fail safe
                ).also { compositeDisposable.add(it) }
        }
    }
}