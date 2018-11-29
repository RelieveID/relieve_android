package com.relieve.android.screen.fragment.dashboard

import androidx.lifecycle.ViewModelProviders
import com.relieve.android.R
import com.relieve.android.rsux.framework.RsuxFragment
import com.relieve.android.screen.viewmodel.DashboardViewHolder

class DashboardChatFragment : RsuxFragment<DashboardViewHolder.DashboardState, DashboardViewHolder>() {
    override val vModel by lazy { ViewModelProviders.of(this).get(DashboardViewHolder::class.java) }

    init {
        layoutId = R.layout.fragment_home_empty_chat
    }

    override fun render(state: DashboardViewHolder.DashboardState) {

    }
}