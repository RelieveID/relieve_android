package com.relieve.android.lib_rsux.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class RelieveViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(data: Component)
}