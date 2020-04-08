package com.robertomiranda.countdown.eventlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.robertomiranda.countdown.databinding.RowEventBinding
import com.robertomiranda.data.eventdetail.model.Event

class EventAdapter(
    private var dataSet: MutableList<Event> = mutableListOf()
) : RecyclerView.Adapter<EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder =
        EventViewHolder(
            RowEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    fun setData(eventList: List<Event>) {
        with(this.dataSet) {
            clear()
            addAll(eventList)
        }
        notifyDataSetChanged()
    }
}

