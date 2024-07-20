package dev.nonoxy.restaurant_reviews.restaurants.domain.usecases

import dev.nonoxy.restaurant_reviews.data.RequestResult
import dev.nonoxy.restaurant_reviews.data.restaurant_reviews.RestaurantReviewsDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoveFromFavoritesUseCase @Inject constructor(
    private val restaurantReviewsDataSource: RestaurantReviewsDataSource
) {
    internal fun execute(restaurantId: Int): Flow<RequestResult<Unit>> {
        return restaurantReviewsDataSource.removeFromFavorites(id = restaurantId)
    }
}