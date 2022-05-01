package com.fastorassignment.interfaces

import com.fastorassignment.data.model.responses.LoginUserResponse
import com.fastorassignment.data.model.responses.RegisterUserResponse
import com.fastorassignment.data.model.responses.RestaurantsResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.Header

interface RepositoryCallbacks {
    suspend fun registerUser(
        phone: String,
        dialCode: String
    ): Response<RegisterUserResponse>

    suspend fun loginUser(
        phone: String,
        otp: String,
        dialCode: String
    ): Response<LoginUserResponse>

    suspend fun fetchRestaurant(
        cityId: Int,
        authorization: String
    ): Response<RestaurantsResponse>
}