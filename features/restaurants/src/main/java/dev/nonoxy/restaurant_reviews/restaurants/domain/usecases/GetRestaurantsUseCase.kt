package dev.nonoxy.restaurant_reviews.restaurants.domain.usecases

import dev.nonoxy.restaurant_reviews.data.RequestResult
import dev.nonoxy.restaurant_reviews.data.map
import dev.nonoxy.restaurant_reviews.data.restaurant_reviews.RestaurantReviewsDataSource
import dev.nonoxy.restaurant_reviews.data.restaurant_reviews.models.Restaurant
import dev.nonoxy.restaurant_reviews.restaurants.domain.models.RestaurantUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetRestaurantsUseCase @Inject constructor(
    private val restaurantReviewsDataSource: RestaurantReviewsDataSource
) {
    internal fun execute(): Flow<RequestResult<List<RestaurantUI>>> {
        return restaurantReviewsDataSource.getRestaurants()
            .map { result ->
                result.map { restaurants -> restaurants.map { it.toRestaurantUI() } }
            }
    }
}

private fun Restaurant.toRestaurantUI(): RestaurantUI {
    return RestaurantUI(
        id = id,
        name = name,
        photoUrl = photoUrl,
        rate = rate,
        averageCheck = if (averageCheck.size > 0)
            averageCheck.sum().div(averageCheck.size).toString() else "1 000",
        cuisines = cuisines,
        isFavorite = isFavorite
    )
}