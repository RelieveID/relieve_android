package com.relieve.android.rsux.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class RelieveViewHolder<C: Component<C, VH>, VH: RelieveViewHolder<C, VH>>(view: View)
    : RecyclerView.ViewHolder(view) {
    abstract fun bind(data: C)
    abstract fun unbind()
}