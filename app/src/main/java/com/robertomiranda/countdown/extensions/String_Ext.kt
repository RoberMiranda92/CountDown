package com.robertomiranda.countdown.extensions

import java.text.SimpleDateFormat
import java.util.*

inline fun String?.ifEmptyOrNull(defaultValue: () -> String): String =
    if (this.isNullOrEmpty()) defaultValue() else this

fun String.toDate(format: String, locale: Locale = Locale.getDefault()): Date? = SimpleDateFormat(format, locale).parse(this)

fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}