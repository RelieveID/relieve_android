package com.relieve.android.components

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.LayoutParams
import androidx.core.view.setMargins
import com.relieve.android.R
import com.relieve.android.base.Component
import com.relieve.android.base.RelieveViewHolder
import com.relieve.android.helper.dptoPx
import com.relieve.android.helper.secondToTimeText
import kotlinx.android.synthetic.main.view_disaster_news.view.*

class DisasterItem(val latitude: Long,
                   val longitude: Long,
                   val title: String,
                   val subtitle: String) : Component {

    companion object {
        val VIEW_TYPE = DisasterItem::class.java.hashCode()
        fun createViewHolder(ctx: Context, parent: ViewGroup?) : ViewHolder {
            return ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.view_disaster_live, parent, false))
        }
    }

    override val viewType: Int
        get() = VIEW_TYPE

    class ViewHolder(val view: View) : RelieveViewHolder(view) {
        override fun bind(data: Component) {
            if (data is DisasterItem) {
                view.tvDisasterTitle.text = data.title.capitalize()
                view.tvDisasterSubtitle.text = data.subtitle.capitalize()
            }
        }
    }
}