package dev.nonoxy.restaurant_reviews.restaurantdetail.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.nonoxy.restaurant_reviews.restaurantdetail.presentation.models.RestaurantDetailEvent
import dev.nonoxy.restaurant_reviews.restaurantdetail.presentation.models.RestaurantDetailViewState
import dev.nonoxy.restaurant_reviews.restaurantdetail.presentation.ui.views.RestaurantDetailDescriptionView
import dev.nonoxy.restaurant_reviews.restaurantdetail.presentation.ui.views.RestaurantDetailPagerView
import dev.nonoxy.restaurant_reviews.restaurantdetail.presentation.ui.views.RestaurantDetailRateRow
import dev.nonoxy.restaurant_reviews.restaurantdetail.presentation.ui.views.RestaurantDetailTopBarView

@Composable
internal fun RestaurantDetailView(
    viewState: RestaurantDetailViewState.Display,
    eventHandler: (RestaurantDetailEvent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        RestaurantDetailTopBarView(
            onNavigateBackClicked = { eventHandler(RestaurantDetailEvent.NavigateBackClicked) }
        )
        RestaurantDetailPagerView(photosUrl = viewState.restaurantDetail.photosUrl)
        RestaurantDetailRateRow(
            restaurant = viewState.restaurantDetail,
            onFavoriteIconClick = { eventHandler(RestaurantDetailEvent.FavoriteIconClicked(it)) }
        )
        RestaurantDetailDescriptionView(description = viewState.restaurantDetail.description)
    }
}