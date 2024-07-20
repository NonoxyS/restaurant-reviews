package dev.nonoxy.restaurant_reviews.restaurants.presentation.ui.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import dev.nonoxy.restaurant_reviews.restaurants.domain.models.RestaurantUI
import dev.nonoxy.restaurant_reviews.restaurants.presentation.models.RestaurantsEvent
import dev.nonoxy.restaurant_reviews.theme.ui.theme.RestaurantReviewsTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun RestaurantsListView(
    restaurants: List<RestaurantUI>,
    eventHandler: (RestaurantsEvent) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = restaurants, key = { it.id }) { restaurant ->
            RestaurantItemView(
                restaurant = restaurant,
                onFavoriteIconClick = { eventHandler(RestaurantsEvent.FavoriteIconClicked(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(26.dp))
                    .background(RestaurantReviewsTheme.colors.primaryContainer)
                    .animateItemPlacement()
                    .clickable { eventHandler(RestaurantsEvent.RestaurantCardClicked(restaurant.id)) }
                    .padding(bottom = 16.dp)
            )
        }
    }
}