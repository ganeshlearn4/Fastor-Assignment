package com.fastorassignment.app.ui.activities.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fastorassignment.data.enums.RequestResponse
import com.fastorassignment.data.log
import com.fastorassignment.data.model.responses.RestaurantsResponse
import com.fastorassignment.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    val restaurantsData = MutableLiveData<RestaurantsResponse>()

    val status = MutableLiveData(RequestResponse.NONE)

    private val cityId = 118
    val token = MutableLiveData("")

    fun fetchRestaurants() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.fetchRestaurant(cityId, token.value!!)
                if (response.isSuccessful && response.body() != null) {
                    restaurantsData.postValue(response.body())
                    status.postValue(RequestResponse.SUCCESS)
                } else {
                    status.postValue(RequestResponse.ERROR)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                log(e.localizedMessage)
                status.postValue(RequestResponse.NETWORK_ERROR)
            }
        }
    }
}