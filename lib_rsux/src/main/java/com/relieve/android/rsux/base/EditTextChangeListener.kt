package com.relieve.android.rsux.base

import android.text.Editable
import android.text.TextWatcher

abstract class EditTextChangeListener : TextWatcher {
    override fun afterTextChanged(s: Editable?) { }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

    abstract override fun onTextChanged(text: CharSequence, start: Int, before: Int, count: Int)
}