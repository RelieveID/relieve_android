package com.relieve.android.rsux.base

import android.view.ViewGroup

interface Component <C: Component<C, VH>, VH: RelieveViewHolder<C, VH>> {
    val viewType: Int
    fun createViewHolder(parent: ViewGroup) : VH
}