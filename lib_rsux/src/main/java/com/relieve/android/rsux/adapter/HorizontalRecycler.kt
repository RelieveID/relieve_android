package com.relieve.android.rsux.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.LayoutParams
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.relieve.android.rsux.base.RelieveViewHolder
import com.relieve.android.rsux.helper.dpToPx
import com.relieve.android.rsux.base.Item
/**
 * Please Provide order if you use multiple horizontal recycler in single screen
 * order = [1..n]
 */
class HorizontalRecycler(val localItem : List<Item<*>>, order: Int = 0) : Item<HorizontalRecycler>, BaseAdapter(){

    override val viewType = HorizontalRecycler::class.java.hashCode() + order

    override fun createViewHolder(parent: ViewGroup): ViewHolder {
        val rv = RecyclerView(parent.context).apply {
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).apply {
                setPadding(0, 8.dpToPx(), 0, 8.dpToPx())
            }

            layoutManager = LinearLayoutManager(parent.context,
                LinearLayoutManager.HORIZONTAL, false)

            setRecyclerListener {
                if (it is RelieveViewHolder<*>){
                    it.unbind()
                }
            }

            adapter = this@HorizontalRecycler
        }

        return ViewHolder(rv)
    }

    class ViewHolder (val view: View) : RelieveViewHolder<HorizontalRecycler>(view) {

        override fun bind(data: HorizontalRecycler) {
            data.localItem.forEach { data.add(it) }
        }

        override fun unbind(data: HorizontalRecycler?) {
            data?.items?.clear()
        }
    }
}