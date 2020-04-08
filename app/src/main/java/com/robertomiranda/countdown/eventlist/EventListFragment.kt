package com.robertomiranda.countdown.eventlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.robertomiranda.countdown.R
import com.robertomiranda.countdown.databinding.FragmentEventListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventListFragment : Fragment() {

    private lateinit var binding: FragmentEventListBinding
    val viewModel: EventListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventListBinding.inflate(inflater)

        return with(binding) {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner
            setUpListeners()
            configureViews()
            observeViewModelChanges()
            root
        }
    }

    private fun configureViews() {
        binding.toolbar.title = getString(R.string.event_list_title)
    }

    private fun setUpListeners() {

    }

    private fun observeViewModelChanges() {
        viewModel.eventList.observe(viewLifecycleOwner, Observer { eventList ->
            Log.d("EventListFragment", eventList.toString())
        })
    }
}