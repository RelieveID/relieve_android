package com.relieve.android.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.relieve.android.R
import com.relieve.android.lib_rsux.base.Component
import com.relieve.android.lib_rsux.base.RelieveViewHolder
import com.relieve.android.components.*
import com.relieve.android.helper.dptoPx
import com.relieve.android.lib_rsux.adapter.RvAdapter
import kotlinx.android.synthetic.main.recycler_view_full.view.*

class HomeFragment : Fragment() {
    class Adapter(ctx: Context) : RvAdapter<RelieveViewHolder>(ctx) {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RelieveViewHolder {
            return when (viewType) {
                TitleBarItem.VIEW_TYPE -> { TitleBarItem.createViewHolder(ctx, parent) }
                UserBarItem.VIEW_TYPE -> { UserBarItem.createViewHolder(ctx, parent) }
                StatusBarItem.VIEW_TYPE -> { StatusBarItem.createViewHolder(ctx, parent) }
                HorizontalRecycler.VIEW_TYPE -> {
                    HorizontalRecycler.createViewHolder(ctx) { childParent, childViewType ->
                        when (childViewType) {
                            SpaceItem.VIEW_TYPE -> { SpaceItem.createViewHolder(ctx) }
                            DiscoverItem.VIEW_TYPE -> { DiscoverItem.createViewHolder(ctx, childParent) }
                            FamilyItem.VIEW_TYPE -> { FamilyItem.createViewHolder(ctx, childParent) }
                            else -> object : RelieveViewHolder(View(ctx)) {
                                override fun bind(data: Component) {}
                            }
                        }
                    }
                }
                DiscoverItem.VIEW_TYPE -> { DiscoverItem.createViewHolder(ctx, parent) }
                FamilyItem.VIEW_TYPE -> { FamilyItem.createViewHolder(ctx, parent) }
                else -> object : RelieveViewHolder(View(ctx)) {
                    override fun bind(data: Component) {}
                }
            }
        }
    }

    private lateinit var adapter: Adapter

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.recycler_view_full, container, false).apply {
            adapter = Adapter(context)
            this.rvFull.layoutManager = LinearLayoutManager(context)
            this.rvFull.adapter = adapter
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        render()
    }

    private fun render() {
        adapter.apply {
            add(UserBarItem("Halo", "Muh. Alif Akbar"))
            add(StatusBarItem(".jpg", "Bojongsoang, Bandung Barat"))
            add(TitleBarItem("Discover", "Update informasi terkini bencana di seluruh Indonesia"))
            add(HorizontalRecycler (
                listOf(
                    SpaceItem(8.dptoPx(), LinearLayout.LayoutParams.MATCH_PARENT),
                    DiscoverItem(0, 0, "Palu", 0,true),
                    DiscoverItem(0, 0, "Lombok", 100, false),
                    DiscoverItem(0, 0, "Lombok", 3_000, false),
                    SpaceItem(8.dptoPx(), LinearLayout.LayoutParams.MATCH_PARENT)
                )
            ))
            add(TitleBarItem("Daftar Kerabat", "Pantau kondisi kerabat terdekat anda dimanapun berada"))
            add(HorizontalRecycler (
                listOf(
                    SpaceItem(8.dptoPx(), LinearLayout.LayoutParams.MATCH_PARENT),
                    FamilyItem(".jpg", FamilyStatus.Good, "Ayah"),
                    FamilyItem(".jpg", FamilyStatus.Bad, "Ibu"),
                    FamilyItem(".jpg", FamilyStatus.Unknown, "Kasih Ku") {
                        test()
                    },
                    FamilyItem("", FamilyStatus.Unknown, "", true),
                    SpaceItem(8.dptoPx(), LinearLayout.LayoutParams.MATCH_PARENT)
                )
            ))
        }
    }

    private fun test() {
        context?.let { ctx ->
            BottomSheetDialog(ctx).apply {
                setContentView(layoutInflater.inflate(R.layout.sheet_notice, null))
                show()
            }
        }
    }
}