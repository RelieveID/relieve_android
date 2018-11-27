package com.relieve.android.rsux.base

import android.view.ViewGroup

interface Component

interface Item<C : Component> : Component {
    val viewType: Int
    fun createViewHolder(parent: ViewGroup) : RelieveViewHolder<C>
}