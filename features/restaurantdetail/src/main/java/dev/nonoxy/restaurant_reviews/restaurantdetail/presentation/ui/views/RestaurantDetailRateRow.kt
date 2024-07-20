package dev.nonoxy.restaurant_reviews.restaurantdetail.presentation.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.nonoxy.restaurant_reviews.restaurantdetail.R
import dev.nonoxy.restaurant_reviews.restaurantdetail.domain.models.RestaurantDetailUI
import dev.nonoxy.restaurant_reviews.theme.ui.theme.RestaurantReviewsTheme

@Composable
internal fun RestaurantDetailRateRow(
    restaurant: RestaurantDetailUI,
    onFavoriteIconClick: (RestaurantDetailUI) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                shape = RoundedCornerShape(26.dp),
                color = RestaurantReviewsTheme.colors.primaryContainer
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = restaurant.name,
                style = RestaurantReviewsTheme.typography.titleLarge,
                color = RestaurantReviewsTheme.colors.primaryText
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = { onFavoriteIconClick(restaurant) },
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(
                        if (restaurant.isFavorite) R.drawable.ic_favorite
                        else R.drawable.ic_not_favorite
                    ),
                    contentDescription = null,
                    tint = RestaurantReviewsTheme.colors.primaryIcon,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        RatingAndAverageCheckBar(rate = restaurant.rate, averageCheck = restaurant.averageCheck)
    }
}

@Composable
private fun RatingAndAverageCheckBar(
    rate: Float?,
    averageCheck: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        (1..5).forEach { step ->
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_star),
                contentDescription = null,
                tint = rate?.let { rate ->
                    if (rate >= step) RestaurantReviewsTheme.colors.primaryIcon
                    else RestaurantReviewsTheme.colors.secondaryIcon
                } ?: RestaurantReviewsTheme.colors.secondaryIcon,
                modifier = Modifier.size(16.dp)
            )
            if (step < 5) {
                Spacer(modifier = Modifier.width(3.dp))
            } else Spacer(modifier = Modifier.width(8.dp))
        }
        Text(
            text = rate?.let { "$it" } ?: stringResource(R.string.no_reviews),
            style = RestaurantReviewsTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            color = rate?.let { RestaurantReviewsTheme.colors.primaryIcon } ?: Color.Gray
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "€ $averageCheck",
            style = RestaurantReviewsTheme.typography.bodyMedium,
            color = RestaurantReviewsTheme.colors.secondaryText
        )
    }
}