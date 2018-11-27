package com.relieve.android.components

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.relieve.android.R
import com.relieve.android.rsux.base.Item
import com.relieve.android.rsux.base.RelieveViewHolder
import kotlinx.android.synthetic.main.view_user_bar.view.*

class UserBarItem(val greet: String, val name: String)
    : Item<UserBarItem> {

    override val viewType = UserBarItem::class.java.hashCode()

    override fun createViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_user_bar, parent, false))
    }

    class ViewHolder(val view: View) : RelieveViewHolder<UserBarItem>(view) {
        override fun bind(data: UserBarItem) {
            if (data.greet.isNotEmpty()) view.tvGreet.visibility = View.VISIBLE
            view.tvGreet.text = data.greet.capitalize()
            view.tvName.text = data.name.capitalize()
        }

        override fun unbind() {
            view.tvGreet.visibility = View.GONE
            view.tvGreet.text = null
            view.tvName.text = null
        }
    }
}