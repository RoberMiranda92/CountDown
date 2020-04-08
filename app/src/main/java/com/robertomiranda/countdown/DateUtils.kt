package com.robertomiranda.countdown

import android.app.TimePickerDialog
import android.content.Context
import androidx.fragment.app.FragmentManager
import com.google.android.material.datepicker.MaterialDatePicker
import com.robertomiranda.countdown.extensions.toDate
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
        onTimeSelectedAction(String.format("%02d:%02d", hourOfDay, minute))
    }, hour, minutes, true)

    picker.show()
}

fun showDatePicker(fragmentManager: FragmentManager, onDateSelectedAction: (date: String) -> Unit) {

    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("MM-dd-YYYY", Locale.getDefault())
    MaterialDatePicker.Builder.datePicker()
        .build().apply {
            addOnPositiveButtonClickListener { timeInMilliSeconds ->
                calendar.timeInMillis = timeInMilliSeconds
                calendar.add(Calendar.DATE, 1)
                onDateSelectedAction(dateFormat.format(calendar.time))
            }
        }
        .show(fragmentManager, "datePicker")
}

fun getNowTime(): Long = Calendar.getInstance().time.time

fun isDateBeforeNow(dateString: String, format: String = "MM-dd-yyyy HH:mm"): Boolean {
    val date = dateString.toDate(format)
    val now = Calendar.getInstance().time

    return date?.before(now) == true
}
