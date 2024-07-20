package dev.nonoxy.restaurant_reviews.restaurantdetail.presentation.models

sealed class RestaurantDetailAction {
    data object NavigateBack : RestaurantDetailAction()
    data object FailedToUpdateFavoriteStatus : RestaurantDetailAction()
}