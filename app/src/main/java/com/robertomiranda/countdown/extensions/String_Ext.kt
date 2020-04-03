package com.robertomiranda.countdown.extensions

inline fun String?.ifEmptyOrNull(defaultValue: () -> String): String =
    if (this.isNullOrEmpty()) defaultValue() else this