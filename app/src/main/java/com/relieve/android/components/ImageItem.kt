package com.relieve.android.components

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout.LayoutParams
import androidx.core.content.ContextCompat
import com.relieve.android.R
import com.relieve.android.rsux.base.Item
import com.relieve.android.rsux.base.RelieveViewHolder
import com.relieve.android.helper.dptoPx

class ImageItem : Item<ImageItem> {
    override val viewType = ImageItem::class.java.hashCode()

    override fun createViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(ImageView(parent.context).apply {
            background = ContextCompat.getDrawable(parent.context, R.drawable.rounded_corner_rect_grey_big)
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, 120.dptoPx()).apply {
                setMargins(16.dptoPx(), 0, 16.dptoPx(), 0)
            }
        })
    }

    class ViewHolder(val view: View) : RelieveViewHolder<ImageItem> (view) {

        override fun bind(data: ImageItem) {

        }

        override fun unbind(data: ImageItem) {

        }
    }
}