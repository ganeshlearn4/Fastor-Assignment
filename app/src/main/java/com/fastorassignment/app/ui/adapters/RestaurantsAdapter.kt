package com.fastorassignment.app.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fastorassignment.data.model.responses.Cuisine
import com.fastorassignment.data.model.responses.Currency
import com.fastorassignment.data.model.responses.RestaurantsResponseItem
import com.fastorassignment.databinding.ItemContainerBinding

class RestaurantsAdapter : RecyclerView.Adapter<RestaurantsAdapter.RestaurantsViewHolder>() {
    private var restaurantsData = arrayListOf<RestaurantsResponseItem>()

    fun setRestaurantsData(data: ArrayList<RestaurantsResponseItem>) {
        restaurantsData.clear()
        restaurantsData.addAll(data)
        notifyDataSetChanged()
    }

    inner class RestaurantsViewHolder(private val binding: ItemContainerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setupItemContainer(title: String, currency: Currency, cuisines: List<Cuisine>) {
            val itemsAdapter = RestaurantItemsAdapter(currency, cuisines)
            binding.containerTitle.text = title
            binding.containerRecyclerView.adapter = itemsAdapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantsViewHolder {
        val binding =
            ItemContainerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RestaurantsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantsViewHolder, position: Int) {
        holder.setupItemContainer(
            restaurantsData[position].restaurant_name,
            restaurantsData[position].currency,
            restaurantsData[position].cuisines
        )
    }

    override fun getItemCount(): Int {
        return restaurantsData.size
    }
}