package com.robertomiranda.countdown.eventlist

import androidx.recyclerview.widget.RecyclerView
import com.robertomiranda.countdown.databinding.RowEventBinding
import com.robertomiranda.data.eventdetail.model.Event

class EventViewHolder(private val binding: RowEventBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Event) {
        binding.event = item
    }
}