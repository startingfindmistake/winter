package com.example.winter.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Todo(
    val title: String = "",
    val isChecked: Boolean = false,
) : Parcelable
