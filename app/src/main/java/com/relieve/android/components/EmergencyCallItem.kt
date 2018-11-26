package com.relieve.android.components

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import com.relieve.android.R
import com.relieve.android.rsux.base.Component
import com.relieve.android.rsux.base.RelieveViewHolder
import kotlinx.android.synthetic.main.view_emergency_call.view.*

class EmergencyCallItem(@DrawableRes val icon: Int, val text: String)
    : Component<EmergencyCallItem, EmergencyCallItem.ViewHolder> {

    override val viewType = EmergencyCallItem::class.java.hashCode()

    override fun createViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.view_emergency_call, parent, false))
    }

    class ViewHolder(val view: View) : RelieveViewHolder<EmergencyCallItem, EmergencyCallItem.ViewHolder>(view) {
        override fun bind(data: EmergencyCallItem) {
            view.ivEmergencyFoundationIcon.setImageResource(data.icon)
            view.tvEmergencyFoundation.text = data.text
        }

        override fun unbind() {
            view.ivEmergencyFoundationIcon.setImageDrawable(null)
            view.tvEmergencyFoundation.text = null
        }
    }
}