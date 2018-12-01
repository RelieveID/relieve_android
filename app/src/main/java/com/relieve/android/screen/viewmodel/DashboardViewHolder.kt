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
import retrofit2.HttpException

class DashboardViewHolder : RsuxViewModel<DashboardViewHolder.DashboardState>() {
    companion object {
        private const val SERVER_ERROR = 500
        private const val TOKEN_ERROR = 401
    }

    class DashboardState : RsuxState {
        val earthQuakesLiveData = MutableLiveData<List<Event>>()
        val userLiveData = MutableLiveData<UserData>()
        var page: Int = PAGINATION_START
        var loading: Boolean = false
        var hasReachEnd: Boolean = false

        // handle error auth
        val tokenExpired = MutableLiveData<Boolean>()
        val newToken = MutableLiveData<UserToken>()
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
                    }, { error -> handleError(error) })
                .also { compositeDisposable.add(it) }
        }
    }

    fun discoverTopEvent() {
        discoverNextEvent()
    }

    fun discoverNextEvent() {
        if (!state.hasReachEnd && !state.loading) {
            state.loading = true
            camarService.getEarthQuakes(PAGINATION_LIMIT, state.page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(RETRY_SUM)
                .subscribe(
                    { result ->
                        state.loading = false
                        if (result.status.isRequestSuccess()) {
                            // add page
                            state.page += 1

                            // this will trigger observer
                            val currentData = state.earthQuakesLiveData.value?.toMutableList() ?: mutableListOf()
                            result.data?.let {
                                currentData.addAll(it)
                                state.earthQuakesLiveData.value = currentData
                            }
                        }
                    }, { error ->
                        state.loading = false
                        handleError(error)
                    }
                ).also { compositeDisposable.add(it) }
        }
    }

    fun updateFcmToken(fcmToken: String) {
        relieveService?.run {
            updateFcmToken(UserToken(fcmToken = fcmToken))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(RETRY_SUM)
                .subscribe({}, { error -> handleError(error) })
                .also { compositeDisposable.add(it) }
        }
    }

    fun refreshToken(refreshToken: String) {
        RelieveService.create().refreshToken(UserToken(refreshToken = refreshToken))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .retry(RETRY_SUM)
            .subscribe(
                { result ->
                    if (result.status.isRequestSuccess()) {
                        state.newToken.value = result.content
                        state.tokenExpired.value = false
                    }
                }, { error ->
                    val asd = 1
                })
            .also { compositeDisposable.add(it) }
    }

    override fun handleError(error: Throwable) {
        if (error is HttpException) {
            when (error.code()) {
                SERVER_ERROR -> state.hasReachEnd = true
                TOKEN_ERROR -> state.tokenExpired.value = true
            }
        }
    }
}