package dev.nonoxy.restaurant_reviews.restaurantdetail.presentation.models

import dev.nonoxy.restaurant_reviews.restaurantdetail.domain.models.RestaurantDetailUI

sealed class RestaurantDetailEvent {
    class FetchData(val restaurantId: Int) : RestaurantDetailEvent()
    class FavoriteIconClicked(val restaurant: RestaurantDetailUI) : RestaurantDetailEvent()
    data object NavigateBackClicked : RestaurantDetailEvent()
}