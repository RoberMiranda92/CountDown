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
import com.robertomiranda.countdown.koin.Scopes
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named
import org.koin.java.KoinJavaComponent.getKoin

class CreateEventFragment : Fragment() {

    private lateinit var binding: FragmentCreateEventBinding
    val viewModel: CreateEventViewModel by viewModel()
    private val createEventScope =
        getKoin().getOrCreateScope(Scopes.CREATE_EVENT.name, named(Scopes.CREATE_EVENT.name))

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

        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.save_event -> {
                    viewModel.saveEvent()
                }
            }
            true
        }
    }

    private fun configureViews() {
        binding.eventNameContainer.removeErrorOnTyping()
        binding.toolbar.title = getString(R.string.create_event_title)
    }

    private fun observeViewModelChanges() {
        viewModel.eventNameError.observe(viewLifecycleOwner, Observer {
            binding.eventNameContainer.error = getString(R.string.create_event_event_name_error)
        })

        viewModel.dateError.observe(viewLifecycleOwner, Observer {
            Toast.makeText(
                requireContext(),
                getString(R.string.create_event_event_date_error),
                Toast.LENGTH_SHORT
            ).show()
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

    override fun onDestroy() {
        super.onDestroy()
        createEventScope.close()
    }
}
