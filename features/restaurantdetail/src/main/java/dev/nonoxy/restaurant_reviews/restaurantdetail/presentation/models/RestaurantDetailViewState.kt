package dev.nonoxy.restaurant_reviews.restaurantdetail.presentation.models

import dev.nonoxy.restaurant_reviews.restaurantdetail.domain.models.RestaurantDetailUI

sealed class RestaurantDetailViewState {
    data object Loading : RestaurantDetailViewState()
    data object Error : RestaurantDetailViewState()
    data class Display(
        val restaurantDetail: RestaurantDetailUI
    ) : RestaurantDetailViewState()
}