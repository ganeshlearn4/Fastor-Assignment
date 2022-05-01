package com.fastorassignment.app.ui.activities.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fastorassignment.data.enums.RequestResponse
import com.fastorassignment.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    val phoneNumber = MutableLiveData("")
    val dialCode = "+91"

    val status = MutableLiveData(RequestResponse.NONE)

    fun requestOtp() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.registerUser(phoneNumber.value!!, dialCode)
                if (response.isSuccessful && response.body() != null) {
                    status.postValue(RequestResponse.SUCCESS)
                } else {
                    status.postValue(RequestResponse.ERROR)
                }
            } catch (e: Exception) {
                status.postValue(RequestResponse.NETWORK_ERROR)
            }
        }
    }
}