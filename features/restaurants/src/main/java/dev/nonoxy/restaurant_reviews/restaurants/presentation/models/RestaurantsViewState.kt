package dev.nonoxy.restaurant_reviews.restaurants.presentation.models

import dev.nonoxy.restaurant_reviews.restaurants.domain.models.RestaurantUI

sealed class RestaurantsViewState {
    data object Loading : RestaurantsViewState()
    data object Error : RestaurantsViewState()
    data class Display(
        val favoriteRestaurantsCount: Int,
        val inFavoriteRestaurantsMode: Boolean,
        val restaurants: List<RestaurantUI>,
        val favoriteRestaurants: List<RestaurantUI>,
    ) : RestaurantsViewState()
}