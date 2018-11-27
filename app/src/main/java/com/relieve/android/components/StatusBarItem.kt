package com.relieve.android.components

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.relieve.android.R
import com.relieve.android.rsux.base.Item
import com.relieve.android.rsux.base.RelieveViewHolder
import kotlinx.android.synthetic.main.view_hud_status.view.*

class StatusBarItem(val image: String = "",
                    val location: String = "",
                    isUpdated: Boolean = true) : Item<StatusBarItem> {

    override val viewType = StatusBarItem::class.java.hashCode()

    override fun createViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_hud_status, parent, false))
    }

    class ViewHolder(val view: View) : RelieveViewHolder<StatusBarItem>(view) {
        override fun bind(data: StatusBarItem) {
            view.tvUserLocation.text = data.location.capitalize()
        }

        override fun unbind(data: StatusBarItem) {
            view.tvUserLocation.text = null
        }
    }
}