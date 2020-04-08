package com.robertomiranda.countdown.createevent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robertomiranda.countdown.extensions.ifEmptyOrNull
import com.robertomiranda.countdown.extensions.toDate
import com.robertomiranda.countdown.model.CreateEventRepository
import com.robertomiranda.countdown.model.EventTime
import com.robertomiranda.data.eventdetail.model.Event
import com.robertomiranda.data.eventdetail.repository.EventsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateEventViewModel(
    private val repository: CreateEventRepository,
    private val localEventRepository: EventsRepository
) : ViewModel() {

    //DATA
    private val _eventName = MutableLiveData<String>("")
    val eventName: MutableLiveData<String>
        get() = _eventName

    private val _eventDate = MutableLiveData<String>("")
    val eventDate: MutableLiveData<String>
        get() = _eventDate

    private val _eventTime = MutableLiveData<String>("")
    val eventTime: MutableLiveData<String>
        get() = _eventTime

    //Events
    private val _calculateTimeSuccess = MutableLiveData<EventTime>()
    val calculateTimeSuccess: LiveData<EventTime>
        get() = _calculateTimeSuccess

    //Errors
    private val _eventNameError = MutableLiveData<Unit>()
    val eventNameError: LiveData<Unit>
        get() = _eventNameError
    private val _dateError = MutableLiveData<Unit>()
    val dateError: LiveData<Unit>
        get() = _dateError


    fun startEvent() {

        if (!checkEventIsvalid()) {
            return
        }

        val nowTime = getNowTime()
        val evenStartDate = getEventDate().toDate(DEFAULT_DATE_FORMAT)?.time

        evenStartDate?.let {

            val distanceTime = it.minus(nowTime)

            viewModelScope.launch(Dispatchers.Default) {

                repository.startTimer(1000, distanceTime) { distance ->
                    onEventTimeCalculated(distance)
                }
            }
        }
    }

    private fun checkEventIsvalid(): Boolean {
        val name = getEventName()
        val date = getEventDate()

        if (name.length <= DEFAULT_EVENT_CHARACTERS) {
            _eventNameError.postValue(Unit)
            return false
        }

        if (isDateBeforeNow(date, DEFAULT_DATE_FORMAT)) {
            _dateError.postValue(Unit)
            return false
        }

        return true
    }

    private fun getEventName() = _eventName.value.ifEmptyOrNull { "" }

    private fun getEventDate(): String {
        val date = _eventDate.value.ifEmptyOrNull { "" }
        val time = _eventTime.value.ifEmptyOrNull { DEFAULT_TIME }

        return "$date $time"
    }

    private fun onEventTimeCalculated(calculateTime: EventTime) {
        _calculateTimeSuccess.postValue(calculateTime)
    }

    fun saveEvent() {

        if (!checkEventIsvalid()) {
            return
        }

        viewModelScope.launch {

            val event = Event(eventName = getEventName(), createdAt = getEventDate())

            localEventRepository.insertEvent(event)
        }
    }

    companion object {
        private const val DEFAULT_TIME = "00:00"
        private const val DEFAULT_DATE_FORMAT = "MM-dd-yyyy HH:mm"
        private const val DEFAULT_EVENT_CHARACTERS = 3
    }
}