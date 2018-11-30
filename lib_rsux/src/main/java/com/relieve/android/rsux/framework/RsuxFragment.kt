package com.relieve.android.rsux.framework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.relieve.android.rsux.R
import com.relieve.android.rsux.helper.PreferencesHelper

abstract class RsuxFragment<RS: RsuxState, RVM : RsuxViewModel<RS>> : Fragment() {
    val preferencesHelper by lazy {
        context?.run { PreferencesHelper(this) }
    }

    abstract val vModel : RVM
    val vObserver by lazy { RsuxObserver(this, vModel)  }

    @LayoutRes
    var layoutId = R.layout.blank

    final override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerObserver()
        requestData()
        render(vModel.getCurrentState())
    }

    open fun registerObserver() {}
    open fun requestData() {}

    abstract fun render(state: RS)
}