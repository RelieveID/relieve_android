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
import com.relieve.android.R
import com.relieve.android.adapter.RvAdapter
import com.relieve.android.base.Component
import com.relieve.android.base.RelieveViewHolder
import com.relieve.android.components.DiscoverItem
import com.relieve.android.helper.dptoPx
import kotlinx.android.synthetic.main.recycler_view_full.view.*

class DiscoverFragment : Fragment() {
    companion object {
        const val NUMBER_OF_COLUMN = 2
    }

    class Adapter(ctx: Context) : RvAdapter<RelieveViewHolder>(ctx) {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RelieveViewHolder {
            return when (viewType) {
                DiscoverItem.VIEW_TYPE -> { DiscoverItem.createViewHolder(ctx, parent) }
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
            this.rvHome.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).apply {
                setPadding(8.dptoPx())
            }
            this.rvHome.layoutManager = GridLayoutManager(context, NUMBER_OF_COLUMN)
            this.rvHome.adapter = adapter
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        render()
    }

    private fun render() {
        adapter.apply {
            add(DiscoverItem(0, 0, "Palu", 0,true))
            add(DiscoverItem(0, 0, "Lombok", 100, false))
            add(DiscoverItem(0, 0, "Jakarta", 200, false))
            add(DiscoverItem(0, 0, "Bandung", 300, false))
            add(DiscoverItem(0, 0, "Surabaya", 4000, false))
            add(DiscoverItem(0, 0, "Bali", 500, false))
            add(DiscoverItem(0, 0, "Makassar", 600, false))
            add(DiscoverItem(0, 0, "Lombok", 700, false))
            add(DiscoverItem(0, 0, "Banjarmasin", 800, false))
            add(DiscoverItem(0, 0, "Bali", 1_000, false))
            add(DiscoverItem(0, 0, "Lombok", 2_000, false))
            add(DiscoverItem(0, 0, "Surabaya", 2_500, false))
            add(DiscoverItem(0, 0, "Jakarta", 3_000, false))
            add(DiscoverItem(0, 0, "Jakarta", 3_010, false))
            add(DiscoverItem(0, 0, "Jakarta", 4_000, false))
        }
    }

}