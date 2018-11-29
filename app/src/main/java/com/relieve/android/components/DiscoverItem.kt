package com.relieve.android.components

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.LayoutParams
import androidx.core.view.setMargins
import com.relieve.android.R
import com.relieve.android.rsux.helper.dpToPx
import com.relieve.android.rsux.helper.secondToTimeText
import com.relieve.android.rsux.base.Item
import com.relieve.android.rsux.base.RelieveViewHolder
import kotlinx.android.synthetic.main.view_disaster_news.view.*

class DiscoverItem(val latitude: Double,
                   val longitude: Double,
                   val title: String,
                   val secondAgo: Long,
                   val shouldFillWidth: Boolean = false,
                   val isLive: Boolean = false) : Item<DiscoverItem> {

    override val viewType = DiscoverItem::class.java.hashCode()

    override fun createViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_disaster_news, parent, false))
    }

    class ViewHolder(val view: View) : RelieveViewHolder<DiscoverItem>(view) {
        override fun bind(data: DiscoverItem) {
            view.apply {
                val width = if (data.shouldFillWidth) LayoutParams.MATCH_PARENT else 146.dpToPx()
                layoutParams = LayoutParams(width, LayoutParams.WRAP_CONTENT).apply {
                    setMargins(4.dpToPx())
                }
            }

            view.tvDisasterTitle.text = data.title.capitalize()
            when (data.isLive) {
                true -> {
                    view.tvDisasterLive.visibility = View.VISIBLE
                    view.tvDisasterSubtitle.visibility = View.GONE
                }
                false -> {
                    view.tvDisasterLive.visibility = View.GONE
                    view.tvDisasterSubtitle.visibility = View.VISIBLE

                    view.tvDisasterSubtitle.text = view.context.getString(R.string.disaster_time_template,
                        secondToTimeText(data.secondAgo)
                    )
                }
            }
        }

        override fun unbind(data: DiscoverItem) {
            view.tvDisasterTitle.text = null
            view.tvDisasterSubtitle.text = null
            view.tvDisasterLive.visibility = View.GONE
            view.tvDisasterSubtitle.visibility = View.GONE
        }
    }
}