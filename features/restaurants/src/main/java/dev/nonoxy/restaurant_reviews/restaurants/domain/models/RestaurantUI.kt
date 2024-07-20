package dev.nonoxy.restaurant_reviews.restaurants.domain.models

data class RestaurantUI(
    val id: Int,
    val name: String,
    val photoUrl: String,
    val rate: Float?,
    val averageCheck: String,
    val cuisines: List<String>,
    val isFavorite: Boolean
)