package dev.nonoxy.restaurant_reviews.data.restaurant_reviews

import dev.nonoxy.restaurant_reviews.api.RestaurantReviewsApi
import dev.nonoxy.restaurant_reviews.data.RequestResult
import dev.nonoxy.restaurant_reviews.data.map
import dev.nonoxy.restaurant_reviews.data.restaurant_reviews.models.Restaurant
import dev.nonoxy.restaurant_reviews.data.restaurant_reviews.models.RestaurantDetail
import dev.nonoxy.restaurant_reviews.data.toRequestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge

class RestaurantReviewsDataSource(
    private val restaurantReviewsApi: RestaurantReviewsApi
) {
    fun getRestaurants(): Flow<RequestResult<List<Restaurant>>> {
        val apiRequest = flow { emit(restaurantReviewsApi.getRestaurants()) }.flowOn(Dispatchers.IO)
            .map { result ->
                result.toRequestResult()
                    .map { restaurants ->
                        restaurants.map { it.toRestaurant() }
                    }
            }.flowOn(Dispatchers.Default)

        val start = flowOf<RequestResult<List<Restaurant>>>(RequestResult.InProgress())

        return merge(apiRequest, start)
    }

    fun getRestaurantDetail(id: Int): Flow<RequestResult<RestaurantDetail>> {
        val apiRequest =
            flow { emit(restaurantReviewsApi.getRestaurantById(id)) }.flowOn(Dispatchers.IO)
                .map { result ->
                    result.toRequestResult()
                        .map { it.toRestaurantDetail() }
                }.flowOn(Dispatchers.Default)

        val start = flowOf<RequestResult<RestaurantDetail>>(RequestResult.InProgress())

        return merge(apiRequest, start)
    }

    fun addToFavorites(id: Int): Flow<RequestResult<Unit>> {
        val apiRequest =
            flow { emit(restaurantReviewsApi.addToFavorites(id)) }.flowOn(Dispatchers.IO)
                .map { result -> result.toRequestResult() }.flowOn(Dispatchers.Default)

        val start = flowOf<RequestResult<Unit>>(RequestResult.InProgress())

        return merge(apiRequest, start)
    }

    fun removeFromFavorites(id: Int): Flow<RequestResult<Unit>> {
        val apiRequest =
            flow { emit(restaurantReviewsApi.removeFromFavorites(id)) }.flowOn(Dispatchers.IO)
                .map { result -> result.toRequestResult() }.flowOn(Dispatchers.Default)

        val start = flowOf<RequestResult<Unit>>(RequestResult.InProgress())

        return merge(apiRequest, start)
    }
}