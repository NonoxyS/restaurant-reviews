package dev.nonoxy.restaurant_reviews.restaurantdetail.presentation.models

import dev.nonoxy.restaurant_reviews.restaurantdetail.domain.models.RestaurantDetailUI

sealed class RestaurantDetailViewState {
    data object Loading : RestaurantDetailViewState()
    class Error(val restaurantId: Int) : RestaurantDetailViewState()
    data class Display(
        val restaurantDetail: RestaurantDetailUI
    ) : RestaurantDetailViewState()
}