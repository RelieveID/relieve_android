package com.relieve.android.components.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.LayoutParams
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.relieve.android.rsux.base.RelieveViewHolder
import com.relieve.android.helper.dptoPx
import com.relieve.android.rsux.adapter.RvAdapter
import com.relieve.android.rsux.base.Item

class VerticalGridRecycler <I: Item<I>>
    (private val columnNumber: Int, val spanDecider: (viewType: Int) -> Int)
    : Item<VerticalGridRecycler<I>>, RvAdapter() {

    override val viewType = VerticalGridRecycler::class.java.hashCode()

    override fun createViewHolder(parent: ViewGroup): RelieveViewHolder<Item<*>> {
        val rv = RecyclerView(parent.context).apply {
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).apply {
                setPadding(12.dptoPx(), 0, 12.dptoPx(), 0)
            }

            adapter = this@VerticalGridRecycler

            layoutManager = GridLayoutManager(context, columnNumber).apply {
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int) = this@VerticalGridRecycler.getSpanSize(position)
                }
            }
        }

        return ViewHolder(rv, this)
    }

    fun getSpanSize(position: Int) = if (position < items.size) {
        spanDecider(getItemViewType(position))
    } else {
        1
    }

    class ViewHolder <I: Item<I>>
        (val view: View, private val adapter: VerticalGridRecycler<I>)
        : RelieveViewHolder<Item<*>>(view) {

        override fun bind(data: VerticalGridRecycler<I>) {
            for (component in data.items) {
                adapter.add(component)
            }
            adapter.notifyDataSetChanged()
        }

        override fun unbind() {
            adapter.items.clear()
        }
    }
}