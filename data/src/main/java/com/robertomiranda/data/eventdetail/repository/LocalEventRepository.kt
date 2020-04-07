package com.robertomiranda.data.eventdetail.repository

import com.robertomiranda.data.eventdetail.model.Event
import com.robertomiranda.data.room.EventDao

class LocalEventRepository(private var dao: EventDao) : EventsRepository {

    override suspend fun insertEvent(event: Event) {
        dao.insertEvent(event)
    }

    override suspend fun getEventById(id: Int) {
        dao.getEventById(id)
    }

    override suspend fun removeEventByID(id: Int) {
        dao.deleteEventById(id)
    }
}