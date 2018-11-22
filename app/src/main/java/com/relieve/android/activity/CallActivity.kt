package com.relieve.android.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.relieve.android.R
import com.relieve.android.adapter.RvAdapter
import com.relieve.android.base.Component
import com.relieve.android.base.RelieveViewHolder
import com.relieve.android.components.*
import com.relieve.android.helper.dptoPx
import kotlinx.android.synthetic.main.recycler_view_with_toolbar.*

class CallActivity : AppCompatActivity() {

    private val adapter = object : RvAdapter<RelieveViewHolder>(this) {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RelieveViewHolder {
            return when (viewType) {
                UserBarItem.VIEW_TYPE -> { UserBarItem.createViewHolder(ctx, parent) }
                TitleBarItem.VIEW_TYPE -> { TitleBarItem.createViewHolder(ctx, parent) }
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

        rvWithToolbar.layoutManager = LinearLayoutManager(this)
        rvWithToolbar.adapter = adapter

        render()
    }

    private fun render() {
        adapter.apply {
            add(UserBarItem("", getString(R.string.emergency_call)))
            add(TitleBarItem(getString(R.string.emergency_foundation), ""))
            add(TitleBarItem(getString(R.string.emergency_fam), ""))
            add(
                HorizontalRecycler (
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
