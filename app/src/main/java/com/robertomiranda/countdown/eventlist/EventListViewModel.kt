package com.robertomiranda.countdown.eventlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.robertomiranda.data.eventdetail.model.Event
import com.robertomiranda.data.eventdetail.repository.EventsRepository
import kotlinx.coroutines.Dispatchers

class EventListViewModel(private val localEventRepository: EventsRepository) : ViewModel() {

    val eventList: LiveData<List<Event>> = liveData(Dispatchers.IO) {
            emit(localEventRepository.getAllEvents())
    }
}