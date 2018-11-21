package com.relieve.android.components

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.relieve.android.R
import com.relieve.android.base.Component
import com.relieve.android.base.RelieveViewHolder
import kotlinx.android.synthetic.main.view_user_bar.view.*

class UserBarItem(val greet: String, val name: String) : Component {
    companion object {
        val VIEW_TYPE = UserBarItem::class.java.hashCode()

        fun createViewHolder(ctx: Context, parent: ViewGroup?) : ViewHolder {
            return ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.view_user_bar, parent, false))
        }
    }

    override val viewType: Int
        get() = VIEW_TYPE


    class ViewHolder(val view: View) : RelieveViewHolder(view) {
        override fun bind (data: Component) {
            if (data is UserBarItem) {
                view.tvGreet.text = data.greet.capitalize()
                view.tvName.text = data.name.capitalize()
            }
        }
    }
}