package dev.nonoxy.restaurant_reviews.restaurantdetail.domain.models

data class RestaurantDetailUI(
    val id: Int,
    val name: String,
    val photosUrl: List<String>,
    val description: String,
    val rate: Float?,
    val averageCheck: String,
    val cuisines: List<String>,
    val isFavorite: Boolean
)