package dev.nonoxy.restaurant_reviews.data.restaurant_reviews.models

class RestaurantDetail(
    val id: Int,
    val name: String,
    val detailedInfo: String?,
    val photoUrls: List<String>,
    val rate: Float?,
    val averageCheck: List<Float>,
    val cuisines: List<String>,
    val isFavorite: Boolean
)