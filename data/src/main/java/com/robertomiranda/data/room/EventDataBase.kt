package com.robertomiranda.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.robertomiranda.data.eventdetail.model.Event

@Database(entities = [Event::class], version = 1)
abstract class EventDataBase : RoomDatabase() {

    abstract fun eventDao(): EventDao

    companion object {

        @Volatile
        private var INSTANCE: EventDataBase? = null

        fun getInstance(context: Context): EventDataBase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                EventDataBase::class.java, "Sample.db"
            ).fallbackToDestructiveMigration().build()
    }
}