package com.relieve.android.components

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.relieve.android.R
import com.relieve.android.base.Component
import com.relieve.android.base.RelieveViewHolder
import kotlinx.android.synthetic.main.view_family.view.*

enum class FamilyStatus {
    Good, Bad, Unknown
}

class FamilyItem(val image: String,
                 val status: FamilyStatus,
                 val nickName: String,
                 val isAddBUtton: Boolean = false) : Component {

    companion object {
        val VIEW_TYPE = FamilyItem::class.java.hashCode()
        fun createViewHolder(ctx: Context, parent: ViewGroup?) : ViewHolder {
            return ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.view_family, parent, false))
        }
    }

    override val viewType: Int
        get() = VIEW_TYPE

    class ViewHolder(val view: View) : RelieveViewHolder(view) {
        override fun bind(data: Component) {
            if (data is FamilyItem) {
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
                if (data.isAddBUtton) view.btnAddUser.visibility = View.VISIBLE
            }
        }
    }
}