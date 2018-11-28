package com.relieve.android.rsux.component

import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.BaseTransientBottomBar
import android.view.LayoutInflater
import androidx.core.content.ContextCompat.getColor
import com.relieve.android.rsux.R
import kotlinx.android.synthetic.main.view_snack_bar.view.*


class SnackBarItem(parent : ViewGroup,
                   content: View,
                   contentViewCallback: com.google.android.material.snackbar.ContentViewCallback)
    : BaseTransientBottomBar<SnackBarItem>(parent, content, contentViewCallback) {

    companion object {
        fun make(parent: ViewGroup, duration: Int): SnackBarItem {
            // inflate custom layout
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_snack_bar, parent, false)

            // create with custom view
            val callback = ContentViewCallback(view)
            return SnackBarItem(parent, view, callback).apply {
                this.duration = duration
            }
        }
    }

    init {
        view.setBackgroundColor(getColor(parent.context, android.R.color.white))
    }

    fun setMessage(message: String) = this.apply {
        view.snackBarTitle.text = message
    }

    fun setButtonText(message: String) = this.apply {
        view.snackBarButton.text = message
    }

    fun setButtonClick(click : () -> Unit) = this.apply {
        view.snackBarButton.setOnClickListener { click() }
    }

    class ContentViewCallback (val content: View) : com.google.android.material.snackbar.ContentViewCallback {
        override fun animateContentOut(p0: Int, p1: Int) {
        }

        override fun animateContentIn(p0: Int, p1: Int) {
        }
    }
}