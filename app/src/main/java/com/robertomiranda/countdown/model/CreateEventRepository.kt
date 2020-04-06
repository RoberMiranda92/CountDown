package com.robertomiranda.countdown.model

import java.util.*

class CreateEventRepository() {

    fun calculateTime(milliseconds: Long): EventTime {
        val calendar = Calendar.getInstance()
        calendar.time = Date(milliseconds)

        calendar.get(Calendar.MINUTE)

        return EventTime(
            calendar.get(Calendar.DAY_OF_YEAR),
            calendar.get(Calendar.HOUR),
            calendar.get(Calendar.MINUTE),
            calendar.get(Calendar.SECOND)
        )
    }
}