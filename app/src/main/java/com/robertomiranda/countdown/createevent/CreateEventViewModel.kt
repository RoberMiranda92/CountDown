package com.robertomiranda.countdown.createevent

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.robertomiranda.countdown.ifEmptyOrNull

class CreateEventViewModel : ViewModel() {

    private val _eventName = MutableLiveData<String>("")
    val eventName: MutableLiveData<String>
        get() = _eventName

    private val _eventDate = MutableLiveData<String>("")
    val eventDate: MutableLiveData<String>
        get() = _eventDate

    private val _eventTime = MutableLiveData<String>("")
    val eventTime: MutableLiveData<String>
        get() = _eventTime

    fun startEvent() {
        val name = _eventName.value
        val date = _eventDate.value
        val time = _eventTime.value.ifEmptyOrNull { DEFAULT_TIME }
    }

    companion object {
        private const val DEFAULT_TIME = "00:00"
    }
}