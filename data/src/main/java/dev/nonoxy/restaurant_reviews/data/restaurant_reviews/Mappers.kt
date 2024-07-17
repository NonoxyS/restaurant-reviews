package dev.nonoxy.restaurant_reviews.data.restaurant_reviews

import dev.nonoxy.restaurant_reviews.api.models.RestaurantDTO
import dev.nonoxy.restaurant_reviews.api.models.RestaurantDetailDTO
import dev.nonoxy.restaurant_reviews.data.restaurant_reviews.models.Restaurant
import dev.nonoxy.restaurant_reviews.data.restaurant_reviews.models.RestaurantDetail

internal fun RestaurantDTO.toRestaurant(): Restaurant {
    return Restaurant(
        id = id,
        name = name,
        photoUrl = photoUrl,
        rate = rate,
        averageCheck = averageCheck,
        cuisines = cuisines,
        isFavorite = isFavorite
    )
}

internal fun RestaurantDetailDTO.toRestaurantDetail(): RestaurantDetail {
    return RestaurantDetail(
        id = id,
        name = name,
        detailedInfo = detailedInfo,
        photoUrls = photoUrls,
        rate = rate,
        averageCheck = averageCheck,
        cuisines = cuisines,
        isFavorite = isFavorite
    )
}