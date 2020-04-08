package com.robertomiranda.data.eventdetail.repository

import com.robertomiranda.data.eventdetail.model.Event

interface EventsRepository {
    suspend fun getAllEvents(): List<Event>

    suspend fun insertEvent(event: Event)

    suspend fun getEventById(id: Int): Event

    suspend fun removeEventByID(id: Int)
}