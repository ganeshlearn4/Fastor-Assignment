package com.fastorassignment.data.network

import com.fastorassignment.data.model.responses.LoginUserResponse
import com.fastorassignment.data.model.responses.RegisterUserResponse
import com.fastorassignment.data.model.responses.RestaurantsResponse
import retrofit2.Response
import retrofit2.http.*

interface Api {
    @POST("pwa/user/register")
    @FormUrlEncoded
    suspend fun registerUser(
        @Field("phone") phone: String,
        @Field("dial_code") dialCode: String
    ): Response<RegisterUserResponse>

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("pwa/user/login")
    @FormUrlEncoded
    suspend fun loginUser(
        @Field("phone") phone: String,
        @Field("otp") otp: String,
        @Field("dial_code") dialCode: String
    ): Response<LoginUserResponse>

    @GET("m/restaurant")
    suspend fun fetchRestaurant(
        @Query("city_id") cityId: Int,
        @Header("Authorization") authorization: String
    ): Response<RestaurantsResponse>
}