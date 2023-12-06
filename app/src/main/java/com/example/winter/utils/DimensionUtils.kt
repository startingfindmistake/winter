package com.example.winter.utils

import android.content.res.Resources
import android.util.TypedValue
import androidx.compose.ui.geometry.Offset

fun Number.dp(resources: Resources) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    toFloat(),
    resources.displayMetrics
).toInt()

infix fun Number.pos(other: Number) = Offset(toFloat(), other.toFloat())

fun Offset.size() = Size(x, y)

fun Offset.absoluteValue() = Offset(x.absolvteValue, y.absouteValue)