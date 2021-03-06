package com.robertomiranda.countdown.eventlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.robertomiranda.countdown.R
import com.robertomiranda.countdown.databinding.FragmentEventListBinding
import com.robertomiranda.countdown.koin.Scopes
import com.robertomiranda.countdown.ui.VerticalItemDecorator
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named
import org.koin.java.KoinJavaComponent

class EventListFragment : Fragment() {

    private lateinit var binding: FragmentEventListBinding
    val viewModel: EventListViewModel by viewModel()
    private val adapter: EventAdapter by lazy { EventAdapter() }

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
        binding.list.run {
            adapter = this@EventListFragment.adapter
            addItemDecoration(
                VerticalItemDecorator(
                    resources.getDimension(R.dimen.spacing_x).toInt()
                )
            )
        }
    }

    private fun setUpListeners() {
        binding.createNewEvent.setOnClickListener {
            findNavController().navigate(EventListFragmentDirections.actionEventListToCreateFragment())
        }
    }

    private fun observeViewModelChanges() {
        viewModel.eventList.observe(viewLifecycleOwner, Observer { eventList ->
            Log.d("EventListFragment", eventList.toString())
            adapter.setData(eventList)
        })
    }
}