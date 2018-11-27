package com.relieve.android.components

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.LayoutParams
import com.relieve.android.rsux.base.Item
import com.relieve.android.rsux.base.RelieveViewHolder

class SpaceItem(val width: Int, val height: Int) : Item<SpaceItem> {
    override val viewType = SpaceItem::class.java.hashCode()

    override fun createViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(View(parent.context))
    }

    class ViewHolder(val view: View) : RelieveViewHolder<SpaceItem>(view) {
        override fun bind(data: SpaceItem) {
            view.layoutParams = LayoutParams(data.width, data.height)
        }

        override fun unbind(data: SpaceItem) {

        }
    }
}