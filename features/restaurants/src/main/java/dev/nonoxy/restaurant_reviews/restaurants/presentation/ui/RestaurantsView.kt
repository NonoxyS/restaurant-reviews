package dev.nonoxy.restaurant_reviews.restaurants.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.nonoxy.restaurant_reviews.restaurants.presentation.models.RestaurantsEvent
import dev.nonoxy.restaurant_reviews.restaurants.presentation.models.RestaurantsViewState
import dev.nonoxy.restaurant_reviews.restaurants.presentation.ui.views.RestaurantsListView
import dev.nonoxy.restaurant_reviews.restaurants.presentation.ui.views.RestaurantsTopBarView

@Composable
internal fun RestaurantsView(
    viewState: RestaurantsViewState.Display,
    eventHandler: (RestaurantsEvent) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        RestaurantsTopBarView(
            favoritesCount = viewState.favoriteRestaurantsCount,
            inFavoritesMode = viewState.inFavoriteRestaurantsMode,
            onFavoriteFilterClick = { eventHandler(RestaurantsEvent.ToggleFavoriteFilter) }
        )
        RestaurantsListView(
            restaurants =
            if (viewState.inFavoriteRestaurantsMode) viewState.favoriteRestaurants
            else viewState.restaurants,
            eventHandler = eventHandler
        )
    }
}