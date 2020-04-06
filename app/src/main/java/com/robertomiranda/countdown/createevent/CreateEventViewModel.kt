package com.robertomiranda.countdown.createevent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.robertomiranda.countdown.extensions.ifEmptyOrNull
import com.robertomiranda.countdown.extensions.toDate
import com.robertomiranda.countdown.model.CreateEventRepository
import com.robertomiranda.countdown.model.EventTime

class CreateEventViewModel(private val repository: CreateEventRepository) : ViewModel() {

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
        val name = _eventName.value.ifEmptyOrNull { "" }
        val date = _eventDate.value.ifEmptyOrNull { "" }
        val time = _eventTime.value.ifEmptyOrNull { DEFAULT_TIME }

        if (name.length <= DEFAULT_EVENT_CHARACTERS) {
            _eventNameError.postValue(Unit)
            return
        }

        if (isDateBeforeNow("$date $time", DEFAULT_DATE_FORMAT)) {
            _dateError.postValue(Unit)
            return
        }

        val evenStartDate = "$date $time".toDate(DEFAULT_DATE_FORMAT)?.time
        val nowTime = getNowTime()

        evenStartDate?.let {
            val distanceTime = it.minus(nowTime)
            onEventTimeCalculated(repository.calculateTime(distanceTime.toDouble()))
        }
    }

    private fun onEventTimeCalculated(calculateTime: EventTime) {
        _calculateTimeSuccess.postValue(calculateTime)
    }

    companion object {
        private const val DEFAULT_TIME = "00:00"
        private const val DEFAULT_DATE_FORMAT = "MM-dd-yyyy HH:mm"
        private const val DEFAULT_EVENT_CHARACTERS = 3
    }
}