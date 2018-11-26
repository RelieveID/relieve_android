package com.relieve.android.components

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout.LayoutParams
import androidx.core.content.ContextCompat
import com.relieve.android.R
import com.relieve.android.lib_rsux.base.Component
import com.relieve.android.lib_rsux.base.RelieveViewHolder
import com.relieve.android.helper.dptoPx

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