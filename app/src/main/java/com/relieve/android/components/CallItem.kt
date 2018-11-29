package com.relieve.android.components

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.relieve.android.R
import com.relieve.android.rsux.base.Component
import com.relieve.android.rsux.base.Item
import com.relieve.android.rsux.base.RelieveViewHolder
import kotlinx.android.synthetic.main.view_call.view.*

class CallItem(val title: String,
               val subtitle: String,
               val phone: String) : Component, Item<CallItem> {

    override val viewType = CallItem::class.java.hashCode()

    override fun createViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_call, parent, false))
    }

    class ViewHolder(val view: View) : RelieveViewHolder<CallItem>(view) {
        override fun bind(data: CallItem) {
            view.tvCallTitle.text = data.title.capitalize()
            view.tvCallSubitle.text = data.subtitle.capitalize()
        }

        override fun unbind(data: CallItem) {
            view.tvCallTitle.text = null
            view.tvCallSubitle.text = null
        }
    }
}