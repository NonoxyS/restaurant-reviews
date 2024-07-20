package dev.nonoxy.restaurant_reviews.common.eventbus

sealed class EventBus {
    class ChangeRestaurantFavoriteStatus(
        val restaurantId: Int,
        val isFavorite: Boolean
    ) : EventBus()
}