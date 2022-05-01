package com.fastorassignment.data.model.responses

import com.google.gson.annotations.SerializedName

data class RegisterUserResponse(
    @SerializedName("status") val status: String,
    @SerializedName("status_code") val statusCode: Int,
    @SerializedName("data") val data: String
)