package com.relieve.android.rsux.framework

import androidx.lifecycle.Observer

class RsuxObserver <S : RsuxState, V : RsuxViewModel <S>>
    (private val rsuxFragment: RsuxFragment<S ,V>, private val rsuxViewModel: V) : Observer<Any> {
    override fun onChanged(t: Any?) {
        rsuxFragment.render(rsuxViewModel.state)
    }
}