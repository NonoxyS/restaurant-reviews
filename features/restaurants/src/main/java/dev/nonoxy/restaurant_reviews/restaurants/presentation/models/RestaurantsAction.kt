package dev.nonoxy.restaurant_reviews.restaurants.presentation.models

sealed class RestaurantsAction {
    data object FailedToUpdateFavoriteStatus : RestaurantsAction()
    class NavigateToRestaurantDetail(val restaurantId: Int) : RestaurantsAction()
}