package com.fastorassignment.app.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fastorassignment.R
import com.fastorassignment.app.ui.activities.detail.DetailActivity
import com.fastorassignment.data.model.responses.Cuisine
import com.fastorassignment.data.model.responses.Currency
import com.fastorassignment.databinding.ItemBinding

class RestaurantItemsAdapter(private val currency: Currency, private val cuisines: List<Cuisine>) :
    RecyclerView.Adapter<RestaurantItemsAdapter.RestaurantItemsViewHolder>() {

    inner class RestaurantItemsViewHolder(
        private val context: Context,
        private val binding: ItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun setupItem(cuisine: Cuisine) {
            Glide.with(context)
                .load(cuisine.image)
                .placeholder(R.drawable.ic_baseline_image_150)
                .into(binding.imageView)

            binding.title.text = cuisine.cuisine_name
            binding.addedAt.text = cuisine.added_at

            binding.parent.setOnClickListener {
                val detailActivity = Intent(context, DetailActivity::class.java)
                detailActivity.putExtra("data", cuisine)
                context.startActivity(detailActivity)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantItemsViewHolder {
        val binding =
            ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RestaurantItemsViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: RestaurantItemsViewHolder, position: Int) {
        holder.setupItem(cuisines[position])
    }

    override fun getItemCount(): Int {
        return cuisines.size
    }
}