package dev.nonoxy.restaurant_reviews.data.restaurant_reviews.models

class Restaurant(
    val id: Int,
    val name: String,
    val photoUrl: String,
    val rate: Float?,
    val averageCheck: List<Float>,
    val cuisines: List<String>,
    val isFavorite: Boolean
)
