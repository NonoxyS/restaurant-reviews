package dev.nonoxy.restaurant_reviews.theme.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier

@Composable
fun RestaurantReviewsTheme(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalRestaurantReviewsColor provides LightPalette,
        LocalRestaurantReviewsTypography provides Typography,
        content = {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(RestaurantReviewsTheme.colors.primaryBackground)) {
                content.invoke()
            }
        }
    )
}

object RestaurantReviewsTheme {
    val colors: RestaurantReviewsColors
        @Composable
        get() = LocalRestaurantReviewsColor.current
    val typography: RestaurantReviewsTypography
        @Composable
        get() = LocalRestaurantReviewsTypography.current
}