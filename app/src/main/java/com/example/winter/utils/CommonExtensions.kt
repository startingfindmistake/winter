package com.example.winter.utils

import android.content.Context
import android.widget.Toast
import com.example.winter.ui.screens.MainActivity

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.closeApp() {
    (this as? MainActivity)?.finish()
}

inline fun <T : Any, R> T?.withNotNull(block: (T) -> R): R? {
    return this?.let(block)
}