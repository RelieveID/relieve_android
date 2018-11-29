package com.relieve.android.rsux

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.relieve.android.rsux.helper.PreferencesHelper

abstract class RsuxFragment : Fragment() {
    val preferencesHelper by lazy {
        context?.run { PreferencesHelper(this) }
    }

    abstract val vModel : ViewModel
}