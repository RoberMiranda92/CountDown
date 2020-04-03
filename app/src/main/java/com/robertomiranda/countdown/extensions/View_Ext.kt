package com.robertomiranda.countdown.extensions

import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout?.removeErrorOnTyping() {
    this?.editText?.addTextChangedListener { this.error = null }
}