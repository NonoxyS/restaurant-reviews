package dev.nonoxy.restaurant_reviews.restaurants.presentation.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.nonoxy.restaurant_reviews.restaurants.R
import dev.nonoxy.restaurant_reviews.theme.ui.theme.RestaurantReviewsTheme

@Composable
internal fun RestaurantsTopBarView(
    favoritesCount: Int,
    inFavoritesMode: Boolean,
    onFavoriteFilterClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .fillMaxSize()
        ) {
            Text(
                text = if (inFavoritesMode) stringResource(R.string.favorites)
                else stringResource(R.string.restaurants),
                style = RestaurantReviewsTheme.typography.titleSmall,
                color = RestaurantReviewsTheme.colors.primaryText,
                modifier = Modifier.align(Alignment.Center)
            )
            Box(
                modifier = Modifier.align(Alignment.CenterEnd),
                contentAlignment = Alignment.Center
            ) {
                IconButton(
                    onClick = { onFavoriteFilterClick() },
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(
                            if (inFavoritesMode) R.drawable.ic_favorite
                            else R.drawable.ic_not_favorite
                        ),
                        contentDescription = null,
                        tint = RestaurantReviewsTheme.colors.primaryIcon,
                        modifier = Modifier.size(32.dp)
                    )
                }
                if (favoritesCount in 1..99) {
                    Text(
                        text = "$favoritesCount",
                        style = RestaurantReviewsTheme.typography.bodySmall,
                        color =
                        if (inFavoritesMode) RestaurantReviewsTheme.colors.iconText
                        else RestaurantReviewsTheme.colors.primaryIcon,
                        modifier = Modifier.offset { IntOffset(x = -1, y = -2) }
                    )
                } else if (favoritesCount >= 100) {
                    Text(
                        text = "99+",
                        style = RestaurantReviewsTheme.typography.bodySmall,
                        color =
                        if (inFavoritesMode) RestaurantReviewsTheme.colors.iconText
                        else RestaurantReviewsTheme.colors.primaryIcon,
                        fontSize = 10.sp,
                        modifier = Modifier.offset { IntOffset(x = 0, y = -2) }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RestaurantTopBarView_Preview() {
    RestaurantReviewsTheme {
        RestaurantsTopBarView(
            favoritesCount = 17,
            inFavoritesMode = false,
            onFavoriteFilterClick = {})
    }
}
