package com.relieve.android.components

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout.LayoutParams
import androidx.core.content.ContextCompat
import androidx.core.view.setMargins
import com.relieve.android.R
import com.relieve.android.base.Component
import com.relieve.android.base.RelieveViewHolder
import com.relieve.android.helper.dptoPx
import com.relieve.android.helper.secondToTimeText
import kotlinx.android.synthetic.main.view_disaster_news.view.*

class ImageItem : Component {

    companion object {
        val VIEW_TYPE = ImageItem::class.java.hashCode()
        fun createViewHolder(ctx: Context) : ViewHolder {
            return ViewHolder(ImageView(ctx).apply {
                background = ContextCompat.getDrawable(ctx, R.drawable.rounded_corner_rect_grey_big)
                layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, 120.dptoPx()).apply {
                    setMargins(16.dptoPx(), 0, 16.dptoPx(), 0)
                }
            })
        }
    }

    override val viewType: Int
        get() = VIEW_TYPE

    class ViewHolder(val view: View) : RelieveViewHolder(view) {
        override fun bind(data: Component) {

        }
    }
}