package com.relieve.android.components

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.relieve.android.R
import com.relieve.android.rsux.base.Item
import com.relieve.android.rsux.base.RelieveViewHolder
import kotlinx.android.synthetic.main.view_family.view.*

class FamilyItem(val image: String,
                 val status: FamilyStatus,
                 val nickName: String,
                 val isAddButton: Boolean = false,
                 val onClick: (() -> Unit)? = null) : Item<FamilyItem> {

    enum class FamilyStatus {
        Good, Bad, Unknown
    }

    override val viewType = FamilyItem::class.java.hashCode()

    override fun createViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.view_family, parent, false))
    }

    class ViewHolder(val view: View) : RelieveViewHolder<FamilyItem>(view) {
        override fun bind(data: FamilyItem) {
            view.tvUserName.text = data.nickName.capitalize()
            when (data.status) {
                FamilyStatus.Good -> {
                    view.vDangerBg.visibility = View.INVISIBLE
                    view.vSafeBg.visibility = View.VISIBLE
                }
                FamilyStatus.Bad -> {
                    view.vDangerBg.visibility = View.VISIBLE
                    view.vSafeBg.visibility = View.INVISIBLE
                }
                FamilyStatus.Unknown -> {
                    view.vDangerBg.visibility = View.INVISIBLE
                    view.vSafeBg.visibility = View.INVISIBLE
                }
            }
            if (data.isAddButton) view.btnAddUser.visibility = View.VISIBLE
            view.setOnClickListener {
                data.onClick?.invoke()
            }
        }

        override fun unbind(data: FamilyItem) {
            view.tvUserName.text = null
            view.vDangerBg.visibility = View.INVISIBLE
            view.vSafeBg.visibility = View.INVISIBLE

            view.btnAddUser.visibility = View.GONE
            view.setOnClickListener { }
        }
    }
}