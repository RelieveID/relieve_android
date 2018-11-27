package com.relieve.android.rsux.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.relieve.android.rsux.base.Component
import com.relieve.android.rsux.base.Item
import com.relieve.android.rsux.base.RelieveViewHolder

open class RvAdapter : RecyclerView.Adapter<RelieveViewHolder<Component>>() {

    protected val items = mutableListOf<Item<Component>>()

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int =
        items[position].viewType

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RelieveViewHolder<Component> {
        return items.find { it.viewType == viewType }!!.createViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RelieveViewHolder<Component>, position: Int) {
        holder.unbind(items[position])
        holder.bind(items[position])
    }

    @Throws(TypeCastException::class)
    fun <C : Component> add (component: Item<C>) {
        val currentSize = items.size
        try {
            items.add(component as Item<Component>)
            notifyItemInserted(currentSize)
        } catch (e : TypeCastException) { throw e }
    }

    @Throws(TypeCastException::class)
    fun <C : Component> remove (item: Item<C>) {
        try {
            val index = items.indexOf(item as Item<Component>)
            if (index > -1) {
                items.remove(item)
                notifyItemRemoved(index)
            }
        } catch (e : TypeCastException) { throw e }
    }

    fun remove (index: Int) {
        if (index > -1 && index < items.size) {
            items.removeAt(index)
            notifyItemRemoved(index)
        }
    }

    fun removeAll () {
        items.clear()
        notifyDataSetChanged()
    }
}