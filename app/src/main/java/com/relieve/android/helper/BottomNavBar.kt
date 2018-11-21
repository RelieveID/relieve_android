package com.relieve.android.helper

import android.view.View
import androidx.core.content.ContextCompat
import com.relieve.android.R
import kotlinx.android.synthetic.main.view_bottom_nav.view.*

class BottomNavBar (val view: View) {
    private val buttons = listOf(
        view.ivHome to view.tvHome,
        view.ivDiscover to view.tvDiscover,
        view.ivChat to view.tvChat,
        view.ivProfile to view.tvProfile
    )

    private fun resetButtons() {
        for (button in buttons) {
            button.first.setColorFilter(ContextCompat.getColor(view.context, R.color.colorTextGrey),
                android.graphics.PorterDuff.Mode.SRC_IN)
            button.second.visibility = View.GONE
        }
    }

    private fun selectButton(index: Int) {
        buttons[index].first.setColorFilter(ContextCompat.getColor(view.context,
            R.color.colorPrimary
        ),
            android.graphics.PorterDuff.Mode.SRC_IN)

        buttons[index].second.visibility = View.VISIBLE
    }

    fun setHomeClickListener (callback: () -> Unit) {
        view.homeClickArea.setOnClickListener {
            resetButtons()
            selectButton(0)
            callback()
        }
    }

    fun setDiscoverClickListener (callback: () -> Unit) {
        view.discoverClickArea.setOnClickListener {
            resetButtons()
            selectButton(1)
            callback()
        }
    }

    fun setCallClickListener (callback: () -> Unit) {
        view.callClickArea.setOnClickListener {
            callback()
        }
    }

    fun setChatClickListener (callback: () -> Unit) {
        view.chatClickArea.setOnClickListener {
            resetButtons()
            selectButton(2)
            callback()
        }
    }

    fun setProfileClickListener (callback: () -> Unit) {
        view.profileClickArea.setOnClickListener {
            resetButtons()
            selectButton(3)
            callback()
        }
    }
}