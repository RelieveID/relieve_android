package com.relieve.android.screen.viewmodel

import androidx.lifecycle.MutableLiveData
import com.relieve.android.network.PAGINATION_LIMIT
import com.relieve.android.network.PAGINATION_START
import com.relieve.android.network.RETRY_SUM
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
        val page = MutableLiveData<Int>()
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
                .retry(RETRY_SUM)
                .subscribe (
                    { result ->
                        if (result.status.isRequestSuccess()) {
                            // this will trigger observer
                            state.userLiveData.value = result.content
                        }
                    }, {}
                ).also { compositeDisposable.add(it) }
        }
    }

    fun discoverTopEvent() {
        discoverNextEvent()
    }

    fun discoverNextEvent() {
        val currentPage = state.page.value ?: PAGINATION_START
        camarService.getEarthQuakes(PAGINATION_LIMIT, currentPage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .retry(RETRY_SUM)
            .subscribe (
                { result ->
                    if (result.status.isRequestSuccess()) {
                        // add page
                        state.page.value = currentPage + 1

                        // this will trigger observer
                        val currentData = state.earthQuakesLiveData.value?.toMutableList() ?: mutableListOf()
                        result.data?.let {
                            currentData.addAll(it)
                            state.earthQuakesLiveData.value = currentData
                        }
                    }
                }, {}
            ).also { compositeDisposable.add(it) }
    }

    fun updateFcmToken(fcmToken: String) {
        relieveService?.run {
            updateFcmToken(UserToken(fcmToken = fcmToken))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(RETRY_SUM)
                .subscribe().also { compositeDisposable.add(it) }
        }
    }
}