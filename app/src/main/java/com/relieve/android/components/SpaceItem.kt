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

class SpaceItem(val width: Int, val height: Int) : Component {
    companion object {
        val VIEW_TYPE = SpaceItem::class.java.hashCode()
        fun createViewHolder(ctx: Context): ViewHolder {

            return ViewHolder(View(ctx))
        }
    }
    override val viewType: Int
        get() = VIEW_TYPE

    class ViewHolder(val view: View) : RelieveViewHolder(view) {
        override fun bind(data: Component) {
            if (data is SpaceItem) {
                view.layoutParams = LayoutParams(data.width, data.height)
            }
        }
    }
}