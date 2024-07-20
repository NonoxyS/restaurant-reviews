package dev.nonoxy.restaurant_reviews.restaurantdetail.presentation.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.nonoxy.restaurant_reviews.theme.ui.theme.RestaurantReviewsTheme

@Composable
internal fun RestaurantDetailLoadingView() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(48.dp),
            color = RestaurantReviewsTheme.colors.primaryIcon
        )
    }
}