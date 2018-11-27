package com.relieve.android.components.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.LayoutParams
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.relieve.android.rsux.base.RelieveViewHolder
import com.relieve.android.helper.dptoPx
import com.relieve.android.rsux.adapter.RvAdapter
import com.relieve.android.rsux.base.Item

class HorizontalRecycler<I: Item<I>>
    : Item<HorizontalRecycler<I>>, RvAdapter(){

    override val viewType = HorizontalRecycler::class.java.hashCode()

    override fun createViewHolder(parent: ViewGroup): ViewHolder<I> {
        val rv = RecyclerView(parent.context).apply {
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).apply {
                setPadding(0, 8.dptoPx(), 0, 8.dptoPx())
            }

            layoutManager = LinearLayoutManager(parent.context,
                LinearLayoutManager.HORIZONTAL, false)

            adapter = this@HorizontalRecycler
        }

        return ViewHolder(rv, this@HorizontalRecycler)
    }

    class ViewHolder<I: Item<I>>
        (val view: View, private val adapter: HorizontalRecycler<I>)
        : RelieveViewHolder<HorizontalRecycler<I>>(view) {

        override fun bind(data: HorizontalRecycler<I>) {
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