package com.relieve.android.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.relieve.android.R
import com.relieve.android.rsux.base.Component
import com.relieve.android.rsux.base.RelieveViewHolder
import com.relieve.android.components.*
import com.relieve.android.components.adapter.HorizontalRecycler
import com.relieve.android.components.adapter.VerticalGridRecycler
import com.relieve.android.helper.dptoPx
import com.relieve.android.rsux.adapter.RvAdapter
import kotlinx.android.synthetic.main.recycler_view_with_toolbar.*

class CallActivity : AppCompatActivity() {

    private val adapter = object : RvAdapter<RelieveViewHolder>(this) {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RelieveViewHolder {
            return when (viewType) {
                UserBarItem.VIEW_TYPE -> { UserBarItem.createViewHolder(ctx, parent) }
                TitleBarItem.VIEW_TYPE -> { TitleBarItem.createViewHolder(ctx, parent) }
                LocationPickerItem.VIEW_TYPE -> { LocationPickerItem.createViewHolder(ctx, parent) }
                VerticalGridRecycler.VIEW_TYPE -> {
                    VerticalGridRecycler.createViewHolder(ctx, 2, childViewHolderCreator = { childParent, childViewType ->
                        when (childViewType) {
                            EmergencyCallItem.VIEW_TYPE -> { EmergencyCallItem.createViewHolder(ctx, childParent) }
                            EmergencyOptionItem.VIEW_TYPE -> { EmergencyOptionItem.createViewHolder(ctx, childParent) }
                            else -> object : RelieveViewHolder(View(ctx)) {
                                override fun bind(data: Component) {}
                            }
                        }
                    }, spanDecider = { 1 })
                }
                HorizontalRecycler.VIEW_TYPE -> {
                    HorizontalRecycler.createViewHolder(ctx) { childParent, childViewType ->
                        when (childViewType) {
                            SpaceItem.VIEW_TYPE -> { SpaceItem.createViewHolder(ctx) }
                            FamilyItem.VIEW_TYPE -> { FamilyItem.createViewHolder(ctx, childParent) }
                            else -> object : RelieveViewHolder(View(ctx)) {
                                override fun bind(data: Component) {}
                            }
                        }
                    }
                }
                else -> object : RelieveViewHolder(View(ctx)) {
                    override fun bind(data: Component) {}
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_view_with_toolbar)

        toolbar.setNavigationOnClickListener {
            finish()
        }

        rvWithToolbar.layoutManager = LinearLayoutManager(this)
        rvWithToolbar.adapter = adapter

        render()
    }

    private fun render() {
        adapter.apply {
            add(UserBarItem("", getString(R.string.emergency_call)))
            add(LocationPickerItem())
            add(TitleBarItem(getString(R.string.emergency_foundation), ""))
            add(VerticalGridRecycler(listOf(
                EmergencyCallItem(R.drawable.ic_guard, getString(R.string.emergency_police)),
                EmergencyCallItem(R.drawable.ic_ambulance, getString(R.string.emergency_hospital)),
                EmergencyCallItem(R.drawable.ic_red_cross, getString(R.string.emergency_red_cross)),
                EmergencyCallItem(R.drawable.ic_fire, getString(R.string.emergency_fire_fighter)),
                EmergencyCallItem(R.drawable.ic_flashlight, getString(R.string.emergency_sar)),
                EmergencyOptionItem(R.drawable.ic_others, getString(R.string.emergency_other)) {
                    startActivity(Intent(this@CallActivity, CallListActivity::class.java))
                }
            )))
            add(TitleBarItem(getString(R.string.emergency_fam), ""))
            add(
                HorizontalRecycler(
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
                )
            )
        }
    }

    private fun test() {

    }
}
