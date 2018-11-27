package com.relieve.android.components.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.LayoutParams
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.relieve.android.rsux.base.RelieveViewHolder
import com.relieve.android.helper.dptoPx
import com.relieve.android.rsux.adapter.RvAdapter
import com.relieve.android.rsux.base.Component
import com.relieve.android.rsux.base.Item

class VerticalGridRecycler (val localItem: List<Item<*>>,
                            private val columnNumber: Int,
                            val spanDecider: (viewType: Int) -> Int)
    : Item<VerticalGridRecycler>, RvAdapter() {

    override val viewType = VerticalGridRecycler::class.java.hashCode()

    override fun createViewHolder(parent: ViewGroup): ViewHolder {
        val rv = RecyclerView(parent.context).apply {
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).apply {
                setPadding(12.dptoPx(), 0, 12.dptoPx(), 0)
            }

            layoutManager = GridLayoutManager(context, columnNumber).apply {
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int) = this@VerticalGridRecycler.getSpanSize(position)
                }
            }

            adapter = this@VerticalGridRecycler
        }

        return ViewHolder(rv)
    }

    fun getSpanSize(position: Int) = if (position < items.size) {
        spanDecider(getItemViewType(position))
    } else {
        1
    }

    class ViewHolder (val view: View)
        : RelieveViewHolder<VerticalGridRecycler>(view) {

        override fun bind(data: VerticalGridRecycler) {
            data.localItem.forEach { data.add(it) }
        }

        override fun unbind(data: VerticalGridRecycler) {
            data.items.clear()
        }
    }
}