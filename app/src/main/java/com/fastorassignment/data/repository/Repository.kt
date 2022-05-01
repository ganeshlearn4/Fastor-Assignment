package com.fastorassignment.data.repository

import com.fastorassignment.data.model.responses.LoginUserResponse
import com.fastorassignment.data.model.responses.RegisterUserResponse
import com.fastorassignment.data.model.responses.RestaurantsResponse
import com.fastorassignment.data.network.Api
import com.fastorassignment.interfaces.RepositoryCallbacks
import retrofit2.Response

class Repository(private val api: Api) : RepositoryCallbacks {
    override suspend fun registerUser(
        phone: String,
        dialCode: String
    ): Response<RegisterUserResponse> {
        return api.registerUser(phone, dialCode)
    }

    override suspend fun loginUser(
        phone: String,
        otp: String,
        dialCode: String
    ): Response<LoginUserResponse> {
        return api.loginUser(phone, otp, dialCode)
    }

    override suspend fun fetchRestaurant(
        cityId: Int,
        authorization: String
    ): Response<RestaurantsResponse> {
        return api.fetchRestaurant(cityId, authorization)
    }
}