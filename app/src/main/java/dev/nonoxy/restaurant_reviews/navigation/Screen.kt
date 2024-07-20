package dev.nonoxy.restaurant_reviews.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object Restaurants : Screen()

    @Serializable
    data class RestaurantDetail(val restaurantId: Int) : Screen()
}