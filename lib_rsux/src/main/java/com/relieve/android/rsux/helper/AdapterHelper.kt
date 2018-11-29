package com.relieve.android.rsux.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.setupWithVerticalAdapter() = BaseAdapter().also {
    this@setupWithVerticalAdapter.apply {
        layoutManager = LinearLayoutManager(this@setupWithVerticalAdapter.context)
        adapter = it
    }
}
