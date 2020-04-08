package com.robertomiranda.data.eventdetail.repository

import com.robertomiranda.data.eventdetail.model.Event
import com.robertomiranda.data.room.EventDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalEventRepository(private var dao: EventDao) : EventsRepository {

    override suspend fun getAllEvents() =
        withContext(Dispatchers.IO) {
            dao.getAllEvents()
        }

    override suspend fun insertEvent(event: Event) {
        withContext(Dispatchers.IO) {
            dao.insertEvent(event)
        }
    }

    override suspend fun getEventById(id: Int) =
        withContext(Dispatchers.IO) {
            dao.getEventById(id)
        }

    override suspend fun removeEventByID(id: Int) {
        withContext(Dispatchers.IO) {
            dao.deleteEventById(id)
        }
    }
}