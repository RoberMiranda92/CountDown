package com.robertomiranda.countdown.model

import kotlin.math.floor

class CreateEventRepository() {

    fun calculateTime(milliseconds: Double): EventTime {

        val days = floor(milliseconds / (1000 * 60 * 60 * 24))
        val hours = floor((milliseconds % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60))
        val minutes = floor((milliseconds % (1000 * 60 * 60)) / (1000 * 60))
        val seconds = floor((milliseconds % (1000 * 60)) / 1000)

        return EventTime(
            days.toInt(), hours.toInt(), minutes.toInt(), seconds.toInt()
        )
    }
}