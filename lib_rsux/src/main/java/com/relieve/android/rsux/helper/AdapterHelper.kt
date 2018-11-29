package com.relieve.android.rsux.helper

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.relieve.android.rsux.adapter.BaseAdapter

fun RecyclerView.setupWithBaseAdapter(@RecyclerView.Orientation
                                      orientation: Int = RecyclerView.VERTICAL,
                                      reverseLayout: Boolean = false) = BaseAdapter().also {
    this@setupWithBaseAdapter.apply {
        layoutManager = LinearLayoutManager(this@setupWithBaseAdapter.context, orientation, reverseLayout)
        adapter = it
    }
}
