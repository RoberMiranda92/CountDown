package com.robertomiranda.data.eventdetail.repository

import com.robertomiranda.data.eventdetail.model.Event

interface EventsRepository {

    suspend fun insertEvent(event: Event)

    suspend fun getEventById(id: Int)

    suspend fun removeEventByID(id: Int)
}