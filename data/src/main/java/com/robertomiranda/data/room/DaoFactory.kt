package com.robertomiranda.data.room

import android.content.Context

class DaoFactory {

    companion object {
        fun getEventDao(context: Context) = EventDataBase.getInstance(context).eventDao()
    }
}