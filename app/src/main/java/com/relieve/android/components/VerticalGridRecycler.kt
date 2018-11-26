package com.relieve.android.components

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.LayoutParams
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.relieve.android.lib_rsux.base.Component
import com.relieve.android.lib_rsux.base.RelieveViewHolder
import com.relieve.android.helper.dptoPx
import com.relieve.android.lib_rsux.adapter.RvAdapter

class VerticalGridRecycler(val components: List<Component>) : Component {
    companion object {
        val VIEW_TYPE = VerticalGridRecycler::class.java.hashCode()
        fun createViewHolder(ctx: Context, columnNumber: Int,
                             childViewHolderCreator: (parent: ViewGroup,
                                                      viewType: Int) -> RelieveViewHolder,
                             spanDecider: (viewType: Int) -> Int)
                : ViewHolder {

            val myAdapter = object : RvAdapter<RelieveViewHolder>(ctx) {
                override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RelieveViewHolder {
                    return childViewHolderCreator(parent, viewType)
                }

                fun getSpanSize(position: Int) = if (position < components.size) {
                    spanDecider(getItemViewType(position))
                } else {
                    1
                }
            }

            val rv = RecyclerView(ctx).apply {
                layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).apply {
                        setPadding(12.dptoPx(), 0, 12.dptoPx(), 0)
                    }

                adapter = myAdapter

                layoutManager = GridLayoutManager(context, columnNumber).apply {
                    spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                        override fun getSpanSize(position: Int) = myAdapter.getSpanSize(position)
                    }
                }
            }

            return ViewHolder(rv)
        }
    }
    override val viewType: Int
        get() = VIEW_TYPE

    class ViewHolder(val view: View) : RelieveViewHolder(view) {
        override fun bind(data: Component) {
            if (view is RecyclerView && view.adapter is RvAdapter && data is VerticalGridRecycler) {
                for (component in data.components) {
                    (view.adapter as RvAdapter).add(component)
                }
            }
        }
    }
}