package com.relieve.android.rsux.framework

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class RsuxViewModel<RS : RsuxState> : ViewModel() {
    protected val compositeDisposable = CompositeDisposable()
    abstract val state: RS

    fun getCurrentState() = state

    fun updateState(update : RS.() -> Unit) {
        state.update()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    open fun onDestroy() {
        compositeDisposable.dispose()
    }
}