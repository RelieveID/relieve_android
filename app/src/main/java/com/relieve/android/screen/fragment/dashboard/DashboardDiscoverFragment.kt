package com.relieve.android.screen.fragment.dashboard

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.relieve.android.R
import com.relieve.android.components.DisasterItem
import com.relieve.android.components.DiscoverItem
import com.relieve.android.components.TitleBarItem
import com.relieve.android.helper.token
import com.relieve.android.rsux.adapter.EndlessRecyclerViewScrollListener
import com.relieve.android.rsux.adapter.VerticalGridRecycler
import com.relieve.android.rsux.base.Item
import com.relieve.android.rsux.framework.RsuxFragment
import com.relieve.android.rsux.helper.getTimeDiffInSecond
import com.relieve.android.rsux.helper.setupWithBaseAdapter
import com.relieve.android.screen.viewmodel.DashboardViewHolder
import kotlinx.android.synthetic.main.recycler_view_full.view.*

class DashboardDiscoverFragment : RsuxFragment<DashboardViewHolder.DashboardState, DashboardViewHolder>() {
    companion object {
        const val NUMBER_OF_COLUMN = 2
    }

    override val vModel by lazy {
        ViewModelProviders.of(this).get(DashboardViewHolder::class.java).also {
            it.createRelieveService(preferencesHelper?.token)
        }
    }

    private val adapter get() = view?.rvFull?.setupWithBaseAdapter()

    init {
        layoutId = R.layout.recycler_view_full
    }

    override fun registerObserver() {
        vModel.state.earthQuakesLiveData.observe(this, vObserver)
    }

    override fun requestData() {
        adapter?.removeAll() // instantiate adapter by lazy
        view?.rvFull?.layoutManager?.let{
            view?.rvFull?.addOnScrollListener(object : EndlessRecyclerViewScrollListener(it){
                override fun onLoadMore() {
                    vModel.discoverNextEvent()
                }
            })
        }

        vModel.discoverNextEvent()
    }

    override fun render(state: DashboardViewHolder.DashboardState) {
        adapter?.run {
            removeAll()

            add(DisasterItem(0.0, 0.0,"Gunung Semeru Meletus", "Probolinggo, Jawa Timur"))
            add(TitleBarItem("Highlight Bencana", ""))
            state.earthQuakesLiveData.value?.run {

                val discoverList : MutableList<Item<*>>  = this.map {
                    val longitude = it.location?.coordinates?.get(0) ?: 0.0
                    val latitude = it.location?.coordinates?.get(1) ?: 0.0
                    val title = it.eventDetail?.title ?: ""
                    val time = it.time.getTimeDiffInSecond()
                    val place = it.eventDetail?.place ?: ""

                    DiscoverItem(longitude, latitude, title, time, true)
                }.toMutableList()

                add(VerticalGridRecycler (discoverList, NUMBER_OF_COLUMN){ 1 })
            }
        }
    }
}