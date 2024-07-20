package dev.nonoxy.restaurant_reviews.restaurantdetail.presentation.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.nonoxy.restaurant_reviews.restaurantdetail.R
import dev.nonoxy.restaurant_reviews.theme.ui.theme.RestaurantReviewsTheme

@Composable
internal fun RestaurantDetailDescriptionView(
    description: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                shape = RoundedCornerShape(26.dp),
                color = RestaurantReviewsTheme.colors.primaryContainer
            )
            .padding(top = 16.dp, bottom = 16.dp, start = 16.dp, end = 34.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(R.string.description),
            style = RestaurantReviewsTheme.typography.titleLarge,
            color = RestaurantReviewsTheme.colors.primaryText
        )
        Text(
            text = description,
            style = RestaurantReviewsTheme.typography.bodyMedium,
            color = RestaurantReviewsTheme.colors.secondaryText
        )
    }
}