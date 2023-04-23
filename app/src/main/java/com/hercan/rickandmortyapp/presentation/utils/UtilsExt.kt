package com.hercan.rickandmortyapp.presentation.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import java.util.*

fun Context.showError(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun String.capitalize(): String {
    val firstChar = this.substring(0, 1).uppercase(Locale.getDefault())
    val remainingChars = this.substring(1)
    return firstChar + remainingChars
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

