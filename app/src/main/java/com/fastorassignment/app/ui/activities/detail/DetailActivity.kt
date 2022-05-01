package com.fastorassignment.app.ui.activities.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.fastorassignment.R
import com.fastorassignment.data.model.responses.Cuisine
import com.fastorassignment.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    private val viewModel by viewModels<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cuisine = intent.getSerializableExtra("data") as Cuisine
        viewModel.cuisine.value = cuisine

        setListeners()
        observeChanges()
    }

    private fun setListeners() {
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun observeChanges() {
        viewModel.cuisine.observe(this) {
            binding.toolbar.title = it.cuisine_name

            val dataText =
                "Cuisine id: ${it.cuisine_id}\nCuisine name: ${it.cuisine_name}\nRestaurant id: ${it.restaurant_id}\nAdded at: ${it.added_at}}"

            Glide.with(this)
                .load(it.image)
                .placeholder(R.drawable.ic_baseline_image_150)
                .into(binding.imageView)

            binding.dataText.text = dataText
        }
    }
}