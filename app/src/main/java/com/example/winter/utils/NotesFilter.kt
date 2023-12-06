package com.example.winter.utils

sealed class OrderType {
    object Ascending : OrderType()

    object Descending : OrderType()
}

sealed class NotesFilter(val orderType: OrderType) {

    class Date(orderType: OrderType) : NotesFilter(orderType)

    class Title(orderType: OrderType) : NotesFilter(orderType)

    /**
     * return the copy of NotesFileter But with passed OrderType
     */

    fun copy(orderType: OrderType): NotesFilter {
        return when (this) {
            is Title -> Title(orderType = orderType)
            is Date -> Date(orderType = orderType)
        }
    }
}