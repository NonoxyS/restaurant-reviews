package dev.nonoxy.restaurant_reviews.restaurantdetail.domain.usecases

import dev.nonoxy.restaurant_reviews.data.RequestResult
import dev.nonoxy.restaurant_reviews.data.map
import dev.nonoxy.restaurant_reviews.data.restaurant_reviews.RestaurantReviewsDataSource
import dev.nonoxy.restaurant_reviews.data.restaurant_reviews.models.RestaurantDetail
import dev.nonoxy.restaurant_reviews.restaurantdetail.domain.models.RestaurantDetailUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetRestaurantDetailUseCase @Inject constructor(
    private val restaurantReviewsDataSource: RestaurantReviewsDataSource
) {
    internal fun execute(restaurantId: Int): Flow<RequestResult<RestaurantDetailUI>> {
        return restaurantReviewsDataSource.getRestaurantDetail(restaurantId)
            .map { result ->
                result.map { it.toRestaurantDetailUI() }
            }
    }
}

private const val DESCRIPTION = "Aster Bakery — это кафе-пекарня с открытой кухней, " +
        "где посетители могут наблюдать за процессом приготовления хлеба и выпечки.\n" +
        "\nМеню включает в себя разнообразные завтраки, такие как скрэмбл, вареные яйца и" +
        " авокадо-тосты, а также основное меню с блюдами, такими как ризотто и капкейки." +
        " Гости высоко оценивают выпечку, особенно шоколадный торт и краффин.\nЦены в кафе средние."

private fun RestaurantDetail.toRestaurantDetailUI(): RestaurantDetailUI {
    return RestaurantDetailUI(
        id = id,
        name = name,
        photosUrl = photoUrls,
        description = detailedInfo?.let { if (it.isNotBlank()) it else DESCRIPTION } ?: DESCRIPTION,
        rate = rate,
        averageCheck = if (averageCheck.size > 0)
            averageCheck.sum().div(averageCheck.size).toString() else "1 000",
        cuisines = cuisines,
        isFavorite = isFavorite
    )
}