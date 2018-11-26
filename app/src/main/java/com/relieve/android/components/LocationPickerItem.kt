package com.relieve.android.components

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.relieve.android.R
import com.relieve.android.lib_rsux.base.Component
import com.relieve.android.lib_rsux.base.RelieveViewHolder
import kotlinx.android.synthetic.main.view_user_bar.view.*

class LocationPickerItem() : Component {
    companion object {
        val VIEW_TYPE = LocationPickerItem::class.java.hashCode()

        fun createViewHolder(ctx: Context, parent: ViewGroup?) : ViewHolder {
            return ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.view_location_picker, parent, false))
        }
    }

    override val viewType: Int
        get() = VIEW_TYPE


    class ViewHolder(val view: View) : RelieveViewHolder(view) {
        override fun bind (data: Component) {
//            if (data is LocationPickerItem) {
//                if (data.greet.isNotEmpty()) view.tvGreet.visibility = View.VISIBLE
//                view.tvGreet.text = data.greet.capitalize()
//                view.tvName.text = data.name.capitalize()
//            }
        }
    }
}