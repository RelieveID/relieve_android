package com.relieve.android.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.LayoutParams
import androidx.core.view.setMargins
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.relieve.android.R
import com.relieve.android.rsux.helper.dpToPx
import com.relieve.android.rsux.helper.secondToTimeText
import com.relieve.android.rsux.base.Item
import com.relieve.android.rsux.base.RelieveViewHolder
import kotlinx.android.synthetic.main.view_disaster_news.view.*
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class DiscoverItem(val latitude: Double,
                   val longitude: Double,
                   val title: String,
                   val secondAgo: Long,
                   val shouldFillWidth: Boolean = false,
                   val isLive: Boolean = false) : Item<DiscoverItem> {

    override val viewType = DiscoverItem::class.java.hashCode()

    override fun createViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.view_disaster_news, parent, false))
    }

    class ViewHolder(val view: View) : RelieveViewHolder<DiscoverItem>(view) {

        var gMap : GoogleMap? = null

        override fun bind(data: DiscoverItem) {
            view.apply {
                val width = if (data.shouldFillWidth) LayoutParams.MATCH_PARENT else 146.dpToPx()
                layoutParams = LayoutParams(width, LayoutParams.WRAP_CONTENT).apply {
                    setMargins(4.dpToPx())
                }
            }

            view.mvDisasterMap.onCreate(null)
            view.mvDisasterMap.getMapAsync {
                it.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(data.latitude, data.longitude), 5f))
                it.addMarker(MarkerOptions().position(LatLng(data.latitude, data.longitude)))
                it.uiSettings.isMapToolbarEnabled = false
                gMap = it
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

        override fun unbind(data: DiscoverItem?) {
            view.tvDisasterTitle.text = null
            view.tvDisasterSubtitle.text = null
            view.tvDisasterLive.visibility = View.GONE
            view.tvDisasterSubtitle.visibility = View.GONE

            gMap?.apply {
                clear()
                mapType = GoogleMap.MAP_TYPE_NONE
            }
        }
    }
}