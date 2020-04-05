package com.robertomiranda.countdown.createevent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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
            Toast.makeText(requireContext(),"Error en date", Toast.LENGTH_SHORT ).show()
        })
    }
}
