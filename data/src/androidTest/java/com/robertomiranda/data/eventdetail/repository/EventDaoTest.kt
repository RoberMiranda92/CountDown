package com.robertomiranda.data.eventdetail.repository

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.robertomiranda.data.eventdetail.model.Event
import com.robertomiranda.data.room.EventDataBase
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EventDaoTest {

    private lateinit var database: EventDataBase
    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    @Before
    fun init() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context,
            EventDataBase::class.java
        ).setQueryExecutor(testDispatcher.asExecutor())
            .setTransactionExecutor(testDispatcher.asExecutor())
            .build()
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun insertAndGetAllEvent() {
        testScope.runBlockingTest {
            database.eventDao().insertEvent(EVENT)
            database.eventDao().insertEvent(EVENT_2)

            val list = database.eventDao().getAllEvents()

            Assert.assertEquals(list, listOf(EVENT, EVENT_2))
        }
    }

    @Test
    fun insertAndGetEvent() {
        testScope.runBlockingTest {
            database.eventDao().insertEvent(EVENT)

            val inserted = database.eventDao().getEventById(EVENT.id)

            Assert.assertEquals(inserted, EVENT)
        }
    }

    @Test
    fun deleteAndGetEvent() {

        testScope.runBlockingTest {

            database.eventDao().insertEvent(EVENT)

            val deleted = database.eventDao().deleteEventById(EVENT.id)

            val inserted = database.eventDao().getEventById(EVENT.id)

            Assert.assertEquals(inserted, null)
        }
    }

    companion object {
        private val EVENT = Event(id = 1, eventName = "Name_1", createdAt = "1")
        private val EVENT_2 = Event(id = 2, eventName = "Name_2", createdAt = "1")

    }
}
