package com.relieve.android.rsux.base

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class RelieveViewHolder<C : Component>(view: View)
    : RecyclerView.ViewHolder(view) {
    abstract fun bind(data: C)
    abstract fun unbind()
}