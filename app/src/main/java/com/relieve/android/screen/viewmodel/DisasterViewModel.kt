package com.relieve.android.screen.viewmodel

import com.relieve.android.rsux.framework.RsuxState
import com.relieve.android.rsux.framework.RsuxViewModel

class DisasterViewModel : RsuxViewModel<DisasterViewModel.DisasterState>() {
    class DisasterState : RsuxState

    override val state = DisasterState()
}