package com.relieve.android.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.relieve.android.base.Component
import com.relieve.android.base.RelieveViewHolder

abstract class RvAdapter<VH : RelieveViewHolder>(val ctx: Context) : RecyclerView.Adapter<VH>() {
    private val components = ArrayList<Component>()

    override fun getItemCount(): Int = components.size

    override fun getItemViewType(position: Int): Int = components[position].viewType

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(components[position])
    }

    fun add (component: Component) {
        val currentSize = components.size
        components.add(component)
        notifyItemInserted(currentSize)
    }

    fun remove (component: Component) {
        val index = components.indexOf(component)
        if (index > -1) {
            components.remove(component)
            notifyItemRemoved(index)
        }
    }

    fun remove (index: Int) {
        if (index > -1 && index < components.size) {
            components.removeAt(index)
            notifyItemRemoved(index)
        }
    }
}