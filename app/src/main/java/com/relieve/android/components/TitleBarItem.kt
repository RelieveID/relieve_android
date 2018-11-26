package com.relieve.android.components

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.relieve.android.R
import com.relieve.android.rsux.base.Component
import com.relieve.android.rsux.base.RelieveViewHolder
import kotlinx.android.synthetic.main.view_title_bar.view.*

class TitleBarItem(val title: String = "", val subtitle: String = "") : Component<TitleBarItem, TitleBarItem.ViewHolder> {
    override val viewType = TitleBarItem::class.java.hashCode()

    override fun createViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.view_title_bar, parent, false))
    }

    class ViewHolder(val view: View) : RelieveViewHolder<TitleBarItem, TitleBarItem.ViewHolder>(view) {
        override fun unbind() {
            view.tvTitle.text = null
            view.tvSubtitle.visibility = View.GONE
            view.tvSubtitle.text = null
        }

        override fun bind(data: TitleBarItem) {
            view.tvTitle.text = data.title.capitalize()
            if (data.subtitle.isNotEmpty()) view.tvSubtitle.visibility = View.VISIBLE
            view.tvSubtitle.text = data.subtitle.capitalize()
        }
    }
}