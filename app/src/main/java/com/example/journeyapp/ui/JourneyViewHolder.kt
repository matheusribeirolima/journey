package com.example.journeyapp.ui

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.journeyapp.databinding.ItemJourneyBinding

typealias OnClickJourney = (Journey) -> Unit

class JourneyViewHolder(
    private val binding: ItemJourneyBinding,
    private val onClickJourney: OnClickJourney,
) : RecyclerView.ViewHolder(binding.root) {

    lateinit var currentJourney: Journey

    init {
        itemView.setOnClickListener {
            onClickJourney(currentJourney)
        }
    }

    fun bind(journey: Journey) {
        currentJourney = journey
        binding.tvNumber.text = journey.day
        binding.tvDay.text = journey.literalDay
        binding.tvTitle.text = journey.title
        binding.tvSubTitle.text = journey.subtitle
        binding.vDivider.isVisible = journey.isDividerVisible
    }
}
