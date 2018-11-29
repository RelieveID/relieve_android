package com.relieve.android.screen.fragment.dashboard

import androidx.lifecycle.ViewModelProviders
import com.relieve.android.R
import com.relieve.android.components.DisasterItem
import com.relieve.android.components.DiscoverItem
import com.relieve.android.components.TitleBarItem
import com.relieve.android.rsux.adapter.VerticalGridRecycler
import com.relieve.android.rsux.framework.RsuxFragment
import com.relieve.android.rsux.helper.setupWithBaseAdapter
import com.relieve.android.screen.viewmodel.DashboardViewHolder
import kotlinx.android.synthetic.main.recycler_view_full.view.*

class DashboardDiscoverFragment : RsuxFragment<DashboardViewHolder.DashboardState, DashboardViewHolder>() {
    companion object {
        const val NUMBER_OF_COLUMN = 2
    }

    override val vModel by lazy { ViewModelProviders.of(this).get(DashboardViewHolder::class.java) }

    private val adapter get() = view?.rvFull?.setupWithBaseAdapter()

    init {
        layoutId = R.layout.recycler_view_full
    }

    override fun render(state: DashboardViewHolder.DashboardState) {
        adapter?.run {
            removeAll()

            add(DisasterItem(0.0, 0.0,"Gunung Semeru Meletus", "Probolinggo, Jawa Timur"))
            add(TitleBarItem("Highlight Bencana", ""))
            add(
                VerticalGridRecycler(
                    listOf(
                        DiscoverItem(0.0, 0.0, "Palu", 0, true),
                        DiscoverItem(0.0, 0.0, "Lombok", 100, true),
                        DiscoverItem(0.0, 0.0, "Jakarta", 200, true),
                        DiscoverItem(0.0, 0.0, "Bandung", 300, true),
                        DiscoverItem(0.0, 0.0, "Surabaya", 400, true),
                        DiscoverItem(0.0, 0.0, "Bali", 500, true),
                        DiscoverItem(0.0, 0.0, "Makassar", 600, true),
                        DiscoverItem(0.0, 0.0, "Lombok", 700, true),
                        DiscoverItem(0.0, 0.0, "Banjarmasin", 800, true),
                        DiscoverItem(0.0, 0.0, "Bali", 1_000, true),
                        DiscoverItem(0.0, 0.0, "Lombok", 2_000, true),
                        DiscoverItem(0.0, 0.0, "Surabaya", 2_500, true),
                        DiscoverItem(0.0, 0.0, "Jakarta", 3_000, true),
                        DiscoverItem(0.0, 0.0, "Jakarta", 3_010, true),
                        DiscoverItem(0.0, 0.0, "Jakarta", 4_000, true)
                    ), NUMBER_OF_COLUMN
                ) { 1 }
            )
        }
    }
}