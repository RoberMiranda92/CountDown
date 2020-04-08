package com.robertomiranda.data.room

import com.robertomiranda.data.DataContextWrapper

class DaoFactory {

    companion object {
        fun getEventDao() = EventDataBase.getInstance(DataContextWrapper.context).eventDao()
    }
}