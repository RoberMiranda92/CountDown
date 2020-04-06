package com.robertomiranda.countdown.createevent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.textfield.TextInputEditText
import com.robertomiranda.countdown.R
import com.robertomiranda.countdown.databinding.FragmentCreateEventBinding
import com.robertomiranda.countdown.extensions.removeErrorOnTyping
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateEventFragment : Fragment() {

    private lateinit var binding: FragmentCreateEventBinding
    val viewModel: CreateEventViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentCreateEventBinding.inflate(inflater)

        return with(binding) {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner
            setUpListeners()
            configureViews()
            observeViewModelChanges()
            root
        }
    }

    private fun setUpListeners() {
        binding.datePicker.setOnClickListener {
            showDatePicker(parentFragmentManager) { date ->
                (it as TextInputEditText).setText(date)
            }
        }

        binding.timePicker.setOnClickListener {
            showTimePicker(it.context) { time ->
                (it as TextInputEditText).setText(time)
            }
        }
        binding.startButton.setOnClickListener {
            viewModel.startEvent()
        }
    }

    private fun configureViews() {
        binding.eventNameContainer.removeErrorOnTyping()
    }

    private fun observeViewModelChanges() {
        viewModel.eventNameError.observe(viewLifecycleOwner, Observer {
            binding.eventNameContainer.error = getString(R.string.create_event_event_name_error)
        })

        viewModel.dateError.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), "Error en date", Toast.LENGTH_SHORT).show()
        })

        viewModel.calculateTimeSuccess.observe(viewLifecycleOwner, Observer { eventTime ->
            binding.days.text =
                resources.getQuantityString(
                    R.plurals.create_event_days,
                    eventTime.days,
                    eventTime.days
                )
            binding.hours.text =
                resources.getQuantityString(
                    R.plurals.create_event_hours,
                    eventTime.hours,
                    eventTime.hours
                )
            binding.minutes.text =
                resources.getQuantityString(
                    R.plurals.create_event_minutes,
                    eventTime.minutes,
                    eventTime.minutes
                )
            binding.seconds.text =
                resources.getQuantityString(
                    R.plurals.create_event_seconds,
                    eventTime.seconds,
                    eventTime.seconds
                )
        })
    }
}
