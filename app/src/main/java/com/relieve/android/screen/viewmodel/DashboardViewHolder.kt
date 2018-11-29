package com.relieve.android.screen.viewmodel

import com.relieve.android.rsux.framework.RsuxState
import com.relieve.android.rsux.framework.RsuxViewModel

class DashboardViewHolder : RsuxViewModel<DashboardViewHolder.DashboardState>() {
    class DashboardState : RsuxState

    override val state = DashboardState()
}