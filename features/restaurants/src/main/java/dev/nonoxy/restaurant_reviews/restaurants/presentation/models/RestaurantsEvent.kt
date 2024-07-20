package dev.nonoxy.restaurant_reviews.restaurants.presentation.models

import dev.nonoxy.restaurant_reviews.restaurants.domain.models.RestaurantUI

sealed class RestaurantsEvent {
    data object FetchData : RestaurantsEvent()
    data object ToggleFavoriteFilter : RestaurantsEvent()
    class RestaurantCardClicked(val restaurantId: Int) : RestaurantsEvent()
    class FavoriteIconClicked(val restaurant: RestaurantUI) : RestaurantsEvent()
}