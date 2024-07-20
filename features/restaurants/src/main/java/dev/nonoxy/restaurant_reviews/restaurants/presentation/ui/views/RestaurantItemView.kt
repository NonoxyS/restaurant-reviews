package dev.nonoxy.restaurant_reviews.restaurants.presentation.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.imageLoader
import dev.nonoxy.restaurant_reviews.restaurants.R
import dev.nonoxy.restaurant_reviews.restaurants.domain.models.RestaurantUI
import dev.nonoxy.restaurant_reviews.theme.ui.theme.RestaurantReviewsTheme

@Composable
internal fun RestaurantItemView(
    restaurant: RestaurantUI,
    onFavoriteIconClick: (RestaurantUI) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        AsyncImage(
            model = restaurant.photoUrl,
            contentDescription = null,
            imageLoader = LocalContext.current.imageLoader,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.height(160.dp)
        )
        Spacer(modifier = Modifier.height(14.dp))
        RestaurantNameAndFavoriteRow(
            restaurant = restaurant,
            onFavoriteIconClick = onFavoriteIconClick
        )
        Spacer(modifier = Modifier.height(8.dp))
        RestaurantShortInfoRow(restaurant = restaurant)
    }
}

@Composable
private fun RestaurantNameAndFavoriteRow(
    restaurant: RestaurantUI,
    onFavoriteIconClick: (RestaurantUI) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = restaurant.name,
            style = RestaurantReviewsTheme.typography.titleMedium,
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
}

@Composable
private fun RestaurantShortInfoRow(restaurant: RestaurantUI) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_star),
            contentDescription = null,
            tint = restaurant.rate?.let { RestaurantReviewsTheme.colors.primaryIcon } ?: Color.Gray,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = restaurant.rate?.let { restaurant.rate.toString() }
                ?: stringResource(R.string.no_review),
            style = RestaurantReviewsTheme.typography.bodyMedium,
            color = RestaurantReviewsTheme.colors.primaryText
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "€ ${restaurant.averageCheck}",
            style = RestaurantReviewsTheme.typography.bodyMedium,
            color = RestaurantReviewsTheme.colors.secondaryText
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = restaurant.cuisines.joinToString(", "),
            style = RestaurantReviewsTheme.typography.bodyMedium,
            color = RestaurantReviewsTheme.colors.secondaryText,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}
