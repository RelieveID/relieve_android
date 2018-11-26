package com.relieve.android.components.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.LayoutParams
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.relieve.android.rsux.base.Component
import com.relieve.android.rsux.base.RelieveViewHolder
import com.relieve.android.helper.dptoPx
import com.relieve.android.rsux.adapter.RvAdapter

class HorizontalRecycler
    : Component<HorizontalRecycler, HorizontalRecycler.ViewHolder>,
    RvAdapter<HorizontalRecycler, HorizontalRecycler.ViewHolder>(){

    override val viewType = HorizontalRecycler::class.java.hashCode()

    override fun createViewHolder(parent: ViewGroup): ViewHolder {
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

    class ViewHolder(val view: View, private val adapter: HorizontalRecycler)
        : RelieveViewHolder<HorizontalRecycler, ViewHolder>(view) {

        override fun bind(data: HorizontalRecycler) {
            for (component in data.components) {
                adapter.add(component)
            }
            adapter.notifyDataSetChanged()
        }

        override fun unbind() {
            adapter.components.clear()
        }
    }
}