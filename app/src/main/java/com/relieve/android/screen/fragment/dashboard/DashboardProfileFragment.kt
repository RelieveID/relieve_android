package com.relieve.android.screen.fragment.dashboard

import androidx.lifecycle.ViewModelProviders
import com.relieve.android.R
import com.relieve.android.rsux.framework.RsuxFragment
import com.relieve.android.rsux.helper.setupWithBaseAdapter
import com.relieve.android.screen.viewmodel.DashboardViewHolder
import kotlinx.android.synthetic.main.recycler_view_full.view.*

class DashboardProfileFragment : RsuxFragment<DashboardViewHolder.DashboardState, DashboardViewHolder>() {
    override val vModel by lazy { ViewModelProviders.of(this).get(DashboardViewHolder::class.java) }

    init {
        layoutId = R.layout.recycler_view_full
    }

    private val adapter get() = view?.rvFull?.setupWithBaseAdapter()

    override fun render(state: DashboardViewHolder.DashboardState) {

    }
}