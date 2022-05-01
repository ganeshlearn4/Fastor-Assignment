package com.fastorassignment.data.model.responses

import java.io.Serializable

class RestaurantsResponse : ArrayList<RestaurantsResponseItem>()

data class RestaurantsResponseItem(
    val activated_at: String,
    val active: Boolean,
    val active_plan: String,
    val active_plan_id: Any,
    val address_complete: String,
    val avg_cost_for_two: Int,
    val avg_daily_order_count: Any,
    val avg_order_cost: Any,
    val closes_at: String,
    val cover_image: Any,
    val created_at: String,
    val cuisines: List<Cuisine>,
    val currency: Currency,
    val does_bookings: Boolean,
    val e_type_id: String,
    val free_tier_expire_at: Any,
    val free_trial_eligiblity: Boolean,
    val free_trial_expired: Boolean,
    val image: Any,
    val images: List<Image>,
    val is_close: Boolean,
    val is_close_cafeteria: Any,
    val large_image: Any,
    val lead_id: String,
    val location: Location,
    val location_id: String,
    val logo: Any,
    val m_id: String,
    val merchant_payment_methods: List<String>,
    val next_closes_at: Any,
    val next_opens_at: Any,
    val noti_set: String,
    val opens_at: String,
    val points: String,
    val qr_active: Boolean,
    val rating: Rating,
    val refer_responded: Boolean,
    val restaurant_code: String,
    val restaurant_id: String,
    val restaurant_mode: String,
    val restaurant_name: String,
    val restaurant_type: Any,
    val restaurant_uuid: String,
    val small_image: Any,
    val social_profiles: Any,
    val status: String,
    val table_count: Int,
    val thumbnail_image: Any,
    val type_id: Any
)

data class Cuisine(
    val added_at: String,
    val color: String,
    val counter_id: Any,
    val cuisine_id: Int,
    val cuisine_name: String,
    val image: String,
    val is_deleted: Boolean,
    val is_visible: Boolean,
    val restaurant_id: String,
    val text_color: String
) : Serializable

data class Currency(
    val symbol: String
)

data class Image(
    val url: String
)

data class Location(
    val city_id: Any,
    val city_name: String,
    val country_id: String,
    val location_address: String,
    val location_address_2: String,
    val location_id: Int,
    val location_lat: Double,
    val location_locality: String,
    val location_long: Double,
    val location_zip_code: Int,
    val state_id: Any,
    val state_name: String,
    val update_count: String
)

data class Rating(
    val all: All,
    val count: Int,
    val restaurant_avg_rating: Int
)

data class All(
    val `1`: Int,
    val `2`: Int,
    val `3`: Int,
    val `4`: Int,
    val `5`: Int
)