package com.relieve.android.screen.viewmodel

import com.relieve.android.rsux.framework.RsuxState
import com.relieve.android.rsux.framework.RsuxViewModel

class CallViewModel : RsuxViewModel<CallViewModel.CallState>() {
    class CallState : RsuxState

    override val state = CallState()
}