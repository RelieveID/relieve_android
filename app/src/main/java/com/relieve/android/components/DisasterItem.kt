package com.relieve.android.components

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.relieve.android.R
import com.relieve.android.rsux.base.Component
import com.relieve.android.rsux.base.Item
import com.relieve.android.rsux.base.RelieveViewHolder
import kotlinx.android.synthetic.main.view_disaster_news.view.*

class DisasterItem(val latitude: Long,
                   val longitude: Long,
                   val title: String,
                   val subtitle: String) : Component, Item<DisasterItem> {

    override val viewType = DisasterItem::class.java.hashCode()

    override fun createViewHolder(parent: ViewGroup): RelieveViewHolder<DisasterItem> {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_disaster_live, parent, false))
    }

    class ViewHolder(val view: View) : RelieveViewHolder<DisasterItem>(view) {
        override fun bind(data: DisasterItem) {
            view.tvDisasterTitle.text = data.title.capitalize()
            view.tvDisasterSubtitle.text = data.subtitle.capitalize()
        }

        override fun unbind(data: DisasterItem) {
            view.tvDisasterTitle.text = null
            view.tvDisasterSubtitle.text = null
        }
    }
}