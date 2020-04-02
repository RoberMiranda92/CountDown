package com.robertomiranda.countdown.createevent

import android.app.TimePickerDialog
import android.content.Context
import androidx.fragment.app.FragmentManager
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*

fun showTimePicker(context: Context, onTimeSelectedAction: (time: String) -> Unit) {

    var hour: Int
    var minutes: Int

    with(Calendar.getInstance()) {
        hour = get(Calendar.HOUR)
        minutes = get(Calendar.MINUTE)
    }

    val picker = TimePickerDialog(context, { _, hourOfDay, minute ->
        onTimeSelectedAction(String.format("%d : %d", hourOfDay, minute))
    }, hour, minutes, true)

    picker.show()
}

fun showDatePicker(fragmentManager: FragmentManager, onDateSelectedAction: (date: String) -> Unit) {

    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("dd/MM/YYY", Locale.getDefault())
    MaterialDatePicker.Builder.datePicker()
        .build().apply {
            addOnPositiveButtonClickListener { timeInMilliSeconds ->
                calendar.timeInMillis = timeInMilliSeconds
                onDateSelectedAction(dateFormat.format(calendar.time))
            }
        }
        .show(fragmentManager, "datePicker")
}