package com.relieve.android.components

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.relieve.android.R
import com.relieve.android.rsux.base.Item
import com.relieve.android.rsux.base.RelieveViewHolder

class LocationPickerItem : Item<LocationPickerItem> {
    override val viewType = LocationPickerItem::class.java.hashCode()

    override fun createViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_location_picker, parent, false))
    }

    class ViewHolder(val view: View) : RelieveViewHolder<LocationPickerItem>(view) {
        override fun bind(data: LocationPickerItem) {

        }

        override fun unbind(data: LocationPickerItem) {

        }
    }
}