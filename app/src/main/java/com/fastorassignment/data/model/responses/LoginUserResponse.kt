package com.fastorassignment.data.model.responses

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LoginUserResponse(
    val `data`: Data,
    val status: String,
    val status_code: Int
) : Serializable

data class Data(
    val country_id: Any,
    val dial_code: String,
    val dob: Any,
    val email_verified: Boolean,
    val favorite: Any,
    val first_order: Boolean,
    val food_tags: Any,
    val last_known_location: Any,
    val last_password_update: String,
    val member_since: String,
    val mobile_verified: Boolean,
    val password: String,
    val refer_code: String,
    val refresh_token: String,
    val token: String,
    val user_email: String,
    val user_gender: String,
    val user_id: String,
    val user_image: String,
    val user_mobile: String,
    val user_name: String,
    @SerializedName("user_source") val userSource: String,
    @SerializedName("user_username") val userUsername: Any,
    @SerializedName("workmode") val workMode: Any
)