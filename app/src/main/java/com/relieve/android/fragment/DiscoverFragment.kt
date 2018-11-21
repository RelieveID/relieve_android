package com.relieve.android.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.LayoutParams
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.relieve.android.R
import com.relieve.android.adapter.RvAdapter
import com.relieve.android.base.Component
import com.relieve.android.base.RelieveViewHolder
import com.relieve.android.components.DisasterItem
import com.relieve.android.components.DiscoverItem
import com.relieve.android.components.TitleBarItem
import com.relieve.android.components.VerticalGridRecycler
import com.relieve.android.helper.dptoPx
import kotlinx.android.synthetic.main.recycler_view_full.view.*

class DiscoverFragment : Fragment() {
    companion object {
        const val NUMBER_OF_COLUMN = 2
    }

    class Adapter(ctx: Context) : RvAdapter<RelieveViewHolder>(ctx) {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RelieveViewHolder {
            return when (viewType) {
                TitleBarItem.VIEW_TYPE -> { TitleBarItem.createViewHolder(ctx, parent) }
                DisasterItem.VIEW_TYPE -> { DisasterItem.createViewHolder(ctx, parent) }
                VerticalGridRecycler.VIEW_TYPE -> {
                    VerticalGridRecycler.createViewHolder(ctx, 2,
                        childViewHolderCreator = { childParent, childViewType ->
                            when (childViewType) {
                                DiscoverItem.VIEW_TYPE -> {
                                    DiscoverItem.createViewHolder(ctx, childParent, shouldFillWidth = true)
                                }
                                else -> object : RelieveViewHolder(View(ctx)) {
                                    override fun bind(data: Component) {}
                                }
                            }
                        }, spanDecider = { 1 }
                    )
                }

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
            this.rvHome.adapter = adapter
            this.rvHome.layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        render()
    }

    private fun render() {
        adapter.apply {
            add(DisasterItem(0, 0,"Gunung Semeru Meletus", "Probolinggo, Jawa Timur"))
            add(TitleBarItem("Highlight Bencana", ""))
            add(VerticalGridRecycler(listOf(
                DiscoverItem(0, 0, "Palu", 0,true),
                DiscoverItem(0, 0, "Lombok", 100, false),
                DiscoverItem(0, 0, "Jakarta", 200, false),
                DiscoverItem(0, 0, "Bandung", 300, false),
                DiscoverItem(0, 0, "Surabaya", 400, false),
                DiscoverItem(0, 0, "Bali", 500, false),
                DiscoverItem(0, 0, "Makassar", 600, false),
                DiscoverItem(0, 0, "Lombok", 700, false),
                DiscoverItem(0, 0, "Banjarmasin", 800, false),
                DiscoverItem(0, 0, "Bali", 1_000, false),
                DiscoverItem(0, 0, "Lombok", 2_000, false),
                DiscoverItem(0, 0, "Surabaya", 2_500, false),
                DiscoverItem(0, 0, "Jakarta", 3_000, false),
                DiscoverItem(0, 0, "Jakarta", 3_010, false),
                DiscoverItem(0, 0, "Jakarta", 4_000, false)
            )))
        }
    }

}