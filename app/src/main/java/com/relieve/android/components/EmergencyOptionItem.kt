package com.relieve.android.components

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.relieve.android.R
import com.relieve.android.rsux.base.Item
import com.relieve.android.rsux.base.RelieveViewHolder
import kotlinx.android.synthetic.main.view_emergency_call.view.*

class EmergencyOptionItem(@DrawableRes val icon: Int,
                          val text: String,
                          val onclick: (() -> Unit)? = null)
    : Item<EmergencyOptionItem> {
    override val viewType = EmergencyOptionItem::class.java.hashCode()

    override fun createViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.view_emergency_call, parent, false))
    }

    class ViewHolder(val view: View) : RelieveViewHolder<EmergencyOptionItem>(view) {
        override fun bind(data: EmergencyOptionItem) {
            view.ivEmergencyFoundationIcon.setColorFilter(
                ContextCompat.getColor(view.context, R.color.colorPrimary),
                android.graphics.PorterDuff.Mode.SRC_IN)
            view.tvEmergencyFoundation.setTextColor(
                ContextCompat.getColor(view.context, R.color.colorPrimary))

            view.ivEmergencyFoundationIcon.setImageResource(data.icon)
            view.tvEmergencyFoundation.text = data.text

            view.setOnClickListener { data.onclick?.invoke() }
        }

        override fun unbind(data: EmergencyOptionItem) {
            view.ivEmergencyFoundationIcon.setImageDrawable(null)
            view.tvEmergencyFoundation.text = null

            view.setOnClickListener {  }
        }
    }
}