package com.fastorassignment.app.ui.activities.verifyOtp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fastorassignment.data.enums.RequestResponse
import com.fastorassignment.data.model.responses.LoginUserResponse
import com.fastorassignment.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerifyViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    val phoneNumber = MutableLiveData("")
    val otp = MutableLiveData("")
    private val dialCode = "+91"

    val status = MutableLiveData(RequestResponse.NONE)

    val data = MutableLiveData<LoginUserResponse>()


    fun requestOtp() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.registerUser(phoneNumber.value!!, dialCode)
                if (response.isSuccessful && response.body() != null) {
                    status.postValue(RequestResponse.OTP_RESEND_SUCCESS)
                } else {
                    status.postValue(RequestResponse.OTP_RESEND_FAILED)
                }
            } catch (e: Exception) {
                status.postValue(RequestResponse.NETWORK_ERROR)
            }
        }
    }

    fun loginWithOtp() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.loginUser(
                    phoneNumber.value!!.replace(dialCode, ""),
                    otp.value!!,
                    dialCode
                )
                if (response.isSuccessful && response.body() != null) {
                    data.postValue(response.body())
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