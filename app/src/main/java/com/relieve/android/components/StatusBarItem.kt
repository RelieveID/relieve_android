package com.relieve.android.components

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.relieve.android.R
import com.relieve.android.lib_rsux.base.Component
import com.relieve.android.lib_rsux.base.RelieveViewHolder
import kotlinx.android.synthetic.main.view_hud_status.view.*

class StatusBarItem(val image: String = "", val location: String = "", isUpdated: Boolean = true) :
    Component {
    companion object {
        val VIEW_TYPE = StatusBarItem::class.java.hashCode()
        fun createViewHolder(ctx: Context, parent: ViewGroup?) : ViewHolder {
            return ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.view_hud_status, parent, false))
        }
    }

    override val viewType: Int
        get() = VIEW_TYPE


    class ViewHolder(val view: View) : RelieveViewHolder(view) {
        override fun bind(data: Component) {
            if (data is StatusBarItem) {
                view.tvUserLocation.text = data.location.capitalize()
            }
        }
    }
}