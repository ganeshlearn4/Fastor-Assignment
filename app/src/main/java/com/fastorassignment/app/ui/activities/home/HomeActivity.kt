package com.fastorassignment.app.ui.activities.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.fastorassignment.app.ui.adapters.RestaurantsAdapter
import com.fastorassignment.data.enums.RequestResponse
import com.fastorassignment.data.showLongToast
import com.fastorassignment.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val token = intent.getStringExtra("token")
        viewModel.token.value = token

        setListeners()
        observeChanges()

        viewModel.fetchRestaurants()
    }

    private fun setListeners() {
    }

    private fun observeChanges() {
        viewModel.status.observe(this) {
            when (it) {
                RequestResponse.NONE -> {
                }

                RequestResponse.SUCCESS -> {
                    showLongToast("Restaurants Fetched")
                }

                RequestResponse.ERROR -> {
                    showLongToast("Error fetching Restaurants")
                }

                RequestResponse.NETWORK_ERROR -> {
                    showLongToast("Network error while fetching Restaurants")
                }

                else -> {}
            }
        }

        viewModel.restaurantsData.observe(this) {
            val adapter = RestaurantsAdapter()
            adapter.setRestaurantsData(it)

            binding.containerRecyclerView.adapter = adapter
        }
    }
}