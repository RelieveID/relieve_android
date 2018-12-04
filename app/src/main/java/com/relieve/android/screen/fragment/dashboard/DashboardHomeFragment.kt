package com.relieve.android.screen.fragment.dashboard

import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.relieve.android.R
import com.relieve.android.components.*
import com.relieve.android.helper.token
import com.relieve.android.helper.tokenRefresh
import com.relieve.android.rsux.adapter.HorizontalRecycler
import com.relieve.android.rsux.base.Item
import com.relieve.android.rsux.helper.dpToPx
import com.relieve.android.rsux.component.SpaceItem
import com.relieve.android.rsux.framework.RsuxFragment
import com.relieve.android.rsux.helper.getTimeDiffInSecond
import com.relieve.android.rsux.helper.setupWithBaseAdapter
import com.relieve.android.screen.viewmodel.DashboardViewHolder
import kotlinx.android.synthetic.main.recycler_view_full.view.*
import kotlinx.android.synthetic.main.sheet_notice.*

class DashboardHomeFragment : RsuxFragment<DashboardViewHolder.DashboardState, DashboardViewHolder>() {
    companion object {
        private const val MAX_EVENT = 6
        private const val PAGE_EVENT = 1
    }

    override val vModel by lazy {
        ViewModelProviders.of(this).get(DashboardViewHolder::class.java).also {
            it.createRelieveService(preferencesHelper?.token)
        }
    }

    init {
        layoutId = R.layout.recycler_view_full
    }

    private val adapter get() = view?.rvFull?.setupWithBaseAdapter()

    override fun registerObserver() {
        super.registerObserver()

        vModel.state.earthQuakesLiveData.observe(this, vObserver)
        vModel.state.userLiveData.observe(this, vObserver)
        vModel.state.tokenExpired.observe(this, vObserver)

        // error handler
        vModel.state.tokenExpired.observe(this, Observer { isExpired ->
            if (isExpired) {
                preferencesHelper?.tokenRefresh?.let { vModel.refreshToken(it) }
            } else {
                // retry request
                vModel.createRelieveService(preferencesHelper?.token)
                requestData()
            }
        })

        vModel.state.newToken.observe(this, Observer {
            preferencesHelper?.token = it.newToken
        })
    }

    override fun requestData() {
        vModel.getUserProfile()
        vModel.discoverNextEvent(MAX_EVENT, PAGE_EVENT)
    }

    override fun render(state: DashboardViewHolder.DashboardState) {
        adapter?.run {
            removeAll()

            state.userLiveData.value?.run {
                add(UserBarItem("Halo,", fullname ?: "Muh. Alif Akbar"))
            }
            add(StatusBarItem(".jpg", "Bojongsoang, Bandung Barat"))
            add(TitleBarItem("Discover", "Update informasi terkini bencana di seluruh Indonesia"))
            state.earthQuakesLiveData.value?.run {

                val discoverList : MutableList<Item<*>>  = this.map {
                    val longitude = it.location?.coordinates?.get(0) ?: 0.0
                    val latitude = it.location?.coordinates?.get(1) ?: 0.0
                    val title = it.eventDetail?.title ?: ""
                    val time = it.time.getTimeDiffInSecond()

                    DiscoverItem(latitude, longitude, title, time, false)
                }.toMutableList()

                discoverList.also {
                    it.add(0, SpaceItem(8.dpToPx(), LinearLayout.LayoutParams.MATCH_PARENT))
                    it.add(it.size, SpaceItem(8.dpToPx(), LinearLayout.LayoutParams.MATCH_PARENT))
                }

                add(HorizontalRecycler (discoverList, 1))
            }
            add(TitleBarItem("Daftar Kerabat", "Pantau kondisi kerabat terdekat anda dimanapun berada"))
            add(
                HorizontalRecycler(
                    listOf(
                        SpaceItem(
                            8.dpToPx(),
                            LinearLayout.LayoutParams.MATCH_PARENT
                        ),
                        FamilyItem(".jpg", FamilyItem.FamilyStatus.Good, "Ayah"),
                        FamilyItem(".jpg", FamilyItem.FamilyStatus.Bad, "Ibu"),
                        FamilyItem(".jpg", FamilyItem.FamilyStatus.Unknown, "Kasih Ku") {
                            test()
                        },
                        FamilyItem("", FamilyItem.FamilyStatus.Unknown, "", true),
                        SpaceItem(8.dpToPx(), LinearLayout.LayoutParams.MATCH_PARENT)
                    ), 2
                )
            )
        }
    }

    private fun test() {
        context?.let { ctx ->
            BottomSheetDialog(ctx).apply {
                setContentView(layoutInflater.inflate(R.layout.sheet_notice, null))
                var moveToNext = false
                this.btnPositive.setOnClickListener {
                    moveToNext = true
                    dismiss()
                }

                this.btnNegative.setOnClickListener {
                    moveToNext = true
                    dismiss()
                }
                show()

                setOnDismissListener {
                    if (moveToNext) findNavController().navigate(R.id.action_dashboardFragment_to_disasterFragment)
                }
            }
        }
    }
}