package com.fastorassignment.app.ui.activities.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fastorassignment.data.model.responses.Cuisine
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() : ViewModel() {
    val cuisine = MutableLiveData<Cuisine>()
}