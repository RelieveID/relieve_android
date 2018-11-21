package com.relieve.android.components

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.relieve.android.R
import kotlinx.android.synthetic.main.view_title_bar.view.*

class TitleBar(val title: String = "", val subtitle: String = "") : Component {
    companion object {
        val VIEW_TYPE = TitleBar::class.java.hashCode()
        fun createViewHolder(ctx: Context, parent: ViewGroup?) : ViewHolder {
            return ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.view_title_bar, parent, false))
        }
    }

    override val viewType: Int
        get() = VIEW_TYPE


    class ViewHolder(val view: View) : RelieveViewHolder(view) {
        override fun bind(data: Component) {
            if (data is TitleBar) {
                view.tvTitle.text = data.title.capitalize()
                view.tvSubtitle.text = data.subtitle.capitalize()
            }
        }
    }
}