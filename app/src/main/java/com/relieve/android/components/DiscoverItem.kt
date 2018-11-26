package com.relieve.android.components

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.LayoutParams
import androidx.core.view.setMargins
import com.relieve.android.R
import com.relieve.android.helper.dptoPx
import com.relieve.android.helper.secondToTimeText
import com.relieve.android.lib_rsux.base.Component
import com.relieve.android.lib_rsux.base.RelieveViewHolder
import kotlinx.android.synthetic.main.view_disaster_news.view.*

class DiscoverItem(val latitude: Long,
                   val longitude: Long,
                   val title: String,
                   val secondAgo: Long,
                   val isLive: Boolean = false) : Component {

    companion object {
        val VIEW_TYPE = DiscoverItem::class.java.hashCode()
        fun createViewHolder(ctx: Context, parent: ViewGroup?, shouldFillWidth: Boolean = false) : ViewHolder {
            return ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.view_disaster_news, parent, false).apply {
                val width = if (shouldFillWidth) LayoutParams.MATCH_PARENT else 146.dptoPx()
                layoutParams = LayoutParams(width, LayoutParams.WRAP_CONTENT).apply {
                    setMargins(4.dptoPx())
                }
            })
        }
    }

    override val viewType: Int
        get() = VIEW_TYPE

    class ViewHolder(val view: View) : RelieveViewHolder(view) {
        override fun bind(data: Component) {
            if (data is DiscoverItem) {
                view.tvDisasterTitle.text = data.title.capitalize()
                when (data.isLive) {
                    true -> {
                        view.tvDisasterLive.visibility = View.VISIBLE
                        view.tvDisasterSubtitle.visibility = View.GONE
                    }
                    false -> {
                        view.tvDisasterLive.visibility = View.GONE
                        view.tvDisasterSubtitle.visibility = View.VISIBLE

                        view.tvDisasterSubtitle.text = view.context.getString(R.string.disaster_time_template, secondToTimeText(data.secondAgo))
                    }
                }
            }
        }
    }
}