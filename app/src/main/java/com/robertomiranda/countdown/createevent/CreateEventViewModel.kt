package com.robertomiranda.countdown.createevent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.robertomiranda.countdown.extensions.ifEmptyOrNull

class CreateEventViewModel : ViewModel() {

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

    //Errors
    private val _eventNameError = MutableLiveData<Unit>()
    val eventNameError: LiveData<Unit>
        get() = _eventNameError


    fun startEvent() {
        val name = _eventName.value.ifEmptyOrNull { "" }
        val date = _eventDate.value.ifEmptyOrNull { "" }
        val time = _eventTime.value.ifEmptyOrNull { DEFAULT_TIME }


        if (name.length <= DEFAULT_EVENT_CHARACTERS){
            _eventNameError.postValue(Unit)
        }
    }

    companion object {
        private const val DEFAULT_TIME = "00:00"
        private const val DEFAULT_EVENT_CHARACTERS = 3

    }
}