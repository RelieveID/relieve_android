package com.relieve.android.components.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.LayoutParams
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.relieve.android.rsux.base.RelieveViewHolder
import com.relieve.android.helper.dptoPx
import com.relieve.android.rsux.adapter.RvAdapter
import com.relieve.android.rsux.base.Component
import com.relieve.android.rsux.base.Item
/**
 * Please Provide order if you use multiple horizontal recycler in single screen
 * order = [1..n]
 */
class HorizontalRecycler(val localItem : List<Item<*>>, order: Int = 0) : Item<HorizontalRecycler>, RvAdapter(){

    override val viewType = HorizontalRecycler::class.java.hashCode() + order

    override fun createViewHolder(parent: ViewGroup): ViewHolder {
        val rv = RecyclerView(parent.context).apply {
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).apply {
                setPadding(0, 8.dptoPx(), 0, 8.dptoPx())
            }

            layoutManager = LinearLayoutManager(parent.context,
                LinearLayoutManager.HORIZONTAL, false)

            adapter = this@HorizontalRecycler
        }

        return ViewHolder(rv)
    }

    class ViewHolder (val view: View) : RelieveViewHolder<HorizontalRecycler>(view) {

        override fun bind(data: HorizontalRecycler) {
            data.localItem.forEach { data.add(it) }
        }

        override fun unbind(data: HorizontalRecycler) {
            data.items.clear()
        }
    }
}