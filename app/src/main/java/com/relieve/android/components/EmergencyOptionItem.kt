package com.relieve.android.components

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.relieve.android.R
import com.relieve.android.base.Component
import com.relieve.android.base.RelieveViewHolder
import kotlinx.android.synthetic.main.view_emergency_call.view.*

class EmergencyOptionItem(@DrawableRes val icon: Int, val text: String) : Component {

    companion object {
        val VIEW_TYPE = EmergencyOptionItem::class.java.hashCode()
        fun createViewHolder(ctx: Context, parent: ViewGroup?) : ViewHolder {
            LayoutInflater.from(ctx).inflate(R.layout.view_emergency_call, parent, false)
            return ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.view_emergency_call, parent, false))
        }
    }

    override val viewType: Int
        get() = VIEW_TYPE

    class ViewHolder(val view: View) : RelieveViewHolder(view) {
        override fun bind(data: Component) {
            view.ivEmergencyFoundationIcon.setColorFilter(
                ContextCompat.getColor(view.context, R.color.colorPrimary),
                android.graphics.PorterDuff.Mode.SRC_IN)
            view.tvEmergencyFoundation.setTextColor(
                ContextCompat.getColor(view.context, R.color.colorPrimary))
            if (data is EmergencyOptionItem) {
                view.ivEmergencyFoundationIcon.setImageResource(data.icon)
                view.tvEmergencyFoundation.text = data.text
            }
        }
    }
}