package com.relieve.android.components

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.LayoutParams
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.relieve.android.adapter.RvAdapter
import com.relieve.android.base.Component
import com.relieve.android.base.RelieveViewHolder
import com.relieve.android.helper.dptoPx

class HorizontalRecycler(val components: List<Component>) : Component {
    companion object {
        val VIEW_TYPE = HorizontalRecycler::class.java.hashCode()
        fun createViewHolder(ctx: Context,
                             childViewHolderCreator: (parent: ViewGroup,
                                                      viewType: Int) -> RelieveViewHolder)
                : ViewHolder {

            val myAdapter = object : RvAdapter<RelieveViewHolder>(ctx) {
                override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RelieveViewHolder {
                    return childViewHolderCreator(parent, viewType)
                }
            }

            val rv = RecyclerView(ctx).apply {
                layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).apply {
                        setPadding(0, 8.dptoPx(), 0, 8.dptoPx())
                    }

                layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL, false)
                adapter = myAdapter
            }

            return ViewHolder(rv)
        }
    }
    override val viewType: Int
        get() = VIEW_TYPE

    class ViewHolder(val view: View) : RelieveViewHolder(view) {
        override fun bind(data: Component) {
            if (view is RecyclerView && view.adapter is RvAdapter && data is HorizontalRecycler) {
                for (component in data.components) {
                    (view.adapter as RvAdapter).add(component)
                }
            }
        }
    }
}