package com.example.journeyapp.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.journeyapp.databinding.ItemJourneyBinding
import javax.inject.Inject

class JourneyAdapter @Inject constructor() : RecyclerView.Adapter<JourneyViewHolder>() {

    private val journeys = mutableListOf<Journey>()
    private lateinit var onClickJourney: OnClickJourney

    @SuppressLint("NotifyDataSetChanged")
    fun setJourneys(journeys: List<Journey>) {
        this.journeys.run {
            clear()
            addAll(journeys)
        }
        notifyDataSetChanged()
    }

    fun setOnClickJourney(onClickJourney: OnClickJourney) {
        this.onClickJourney = onClickJourney
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = JourneyViewHolder(
        ItemJourneyBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        onClickJourney
    )

    override fun onBindViewHolder(holder: JourneyViewHolder, position: Int) =
        holder.bind(journeys[position])

    override fun getItemCount() = journeys.size
}
