package com.relieve.android.rsux.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.relieve.android.rsux.base.Component
import com.relieve.android.rsux.base.Item
import com.relieve.android.rsux.base.RelieveViewHolder

abstract class RvAdapter : RecyclerView.Adapter<RelieveViewHolder<Component>>() {

    protected val items = ArrayList<Item<*>>()

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int =
        items[position].viewType

    override fun onBindViewHolder(holder: RelieveViewHolder<Item<*>>, position: Int) {
        holder.unbind()
        holder.bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RelieveViewHolder<Component> =
        items.find { it.viewType == viewType }!!.createViewHolder(parent)


    fun add (component: Item<Component>) {
        val currentSize = items.size
        items.add(component)
        notifyItemInserted(currentSize)
    }

    fun remove (component: Item<Component>) {
        val index = items.indexOf(component)
        if (index > -1) {
            items.remove(component)
            notifyItemRemoved(index)
        }
    }

    fun remove (index: Int) {
        if (index > -1 && index < items.size) {
            items.removeAt(index)
            notifyItemRemoved(index)
        }
    }
}