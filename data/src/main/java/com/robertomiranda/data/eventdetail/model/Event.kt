package com.robertomiranda.data.eventdetail.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "events")
data class Event(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "event_id")
    val id: Int = 0,

    @ColumnInfo(name = "event_name")
    val eventName: String,

    @ColumnInfo(name = "created_at")
    val createdAt: String

) : Serializable