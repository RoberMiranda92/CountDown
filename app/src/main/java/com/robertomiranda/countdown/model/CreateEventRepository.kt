package com.robertomiranda.countdown.model

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlin.math.floor

class CreateEventRepository() {

    private fun calculateTime(milliseconds: Double): EventTime {

        val days = floor(milliseconds / (1000 * 60 * 60 * 24))
        val hours = floor((milliseconds % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60))
        val minutes = floor((milliseconds % (1000 * 60 * 60)) / (1000 * 60))
        val seconds = floor((milliseconds % (1000 * 60)) / 1000)

        return EventTime(
            days.toInt(), hours.toInt(), minutes.toInt(), seconds.toInt()
        )
    }

    suspend fun startTimer(delayMillis: Long = 0, distance: Long = 0,
                           action: (currentDistance: EventTime) -> Unit) {
        var currentDistance = distance
            if (currentDistance > 0) {
                while (true) {
                    action(calculateTime(currentDistance.toDouble()))
                    delay(delayMillis)
                    currentDistance -= delayMillis
                }
            } else {
                action(calculateTime(currentDistance.toDouble()))
            }
    }


}