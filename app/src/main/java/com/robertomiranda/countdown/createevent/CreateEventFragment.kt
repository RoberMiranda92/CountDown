package com.robertomiranda.countdown.createevent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.textview.MaterialTextView
import com.robertomiranda.countdown.databinding.FragmentCreateEventBinding

class CreateEventFragment : Fragment() {


    private lateinit var binding: FragmentCreateEventBinding
    private val viewModel: CreateEventViewModel by viewModels { ViewModelFactory.getInstance() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentCreateEventBinding.inflate(inflater)

        return with(binding) {
            vm = viewModel
            setUpListeners()
            root
        }
    }

    private fun setUpListeners() {
        binding.datePicker.setOnClickListener {
            showDatePicker(parentFragmentManager) { date -> (it as MaterialTextView).text = date }
        }

        binding.timePicker.setOnClickListener {
            showTimePicker(it.context) { time -> (it as TextView).text = time }
        }
    }
}
