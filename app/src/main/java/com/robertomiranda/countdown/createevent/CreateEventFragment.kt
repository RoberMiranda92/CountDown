package com.robertomiranda.countdown.createevent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.robertomiranda.countdown.R
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
            root
        }
    }
}
