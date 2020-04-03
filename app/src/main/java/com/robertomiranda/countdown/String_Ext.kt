package com.robertomiranda.countdown

inline fun String?.ifEmptyOrNull(defaultValue: () -> String): String =
    if (this.isNullOrEmpty()) defaultValue() else this