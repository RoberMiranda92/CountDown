package com.robertomiranda.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.robertomiranda.data.eventdetail.model.Event

@Dao
interface EventDao {

    @Insert
    suspend fun insertEvent(event: Event)

    @Query("SELECT * FROM EVENTS")
    suspend fun getAllEvents(): List<Event>

    @Query("SELECT * FROM events WHERE event_id = :id")
    suspend fun getEventById(id: Int): Event

    @Query("DELETE FROM events WHERE event_id = :id")
    suspend fun deleteEventById(id: Int)

}