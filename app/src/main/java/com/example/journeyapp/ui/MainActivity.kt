package com.example.journeyapp.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.journeyapp.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var journeyAdapter: JourneyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAdapter()

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.uiState.collect { uiState ->
                    when (uiState) {
                        is Result.Loading -> showLoading()
                        is Result.Success -> showJourneys(uiState.result)
                        is Result.Error -> showErrorMessage(uiState.message)
                    }
                }
            }
        }
    }

    private fun setupAdapter() {
        binding.rvJourney.adapter = journeyAdapter
        journeyAdapter.setOnClickJourney { journey ->
            showAlertDialog(journey)
        }
    }

    private fun showAlertDialog(journey: Journey) {
        AlertDialog.Builder(this)
            .setTitle(journey.title)
            .setMessage(journey.subtitle)
            .setPositiveButton(android.R.string.ok) { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun showLoading() {
        binding.pbLoading.isVisible = true
    }

    private fun showJourneys(journeys: List<Journey>) {
        binding.pbLoading.isVisible = false
        journeyAdapter.setJourneys(journeys)
    }

    private fun showErrorMessage(message: String) {
        binding.pbLoading.isVisible = false
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }
}
