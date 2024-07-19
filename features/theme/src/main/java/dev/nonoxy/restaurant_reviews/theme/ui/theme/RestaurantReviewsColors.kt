package dev.nonoxy.restaurant_reviews.theme.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class RestaurantReviewsColors(
    val primaryText: Color,
    val primaryBackground: Color,
    val primaryContainer: Color,
    val secondaryText: Color,
)

internal val LightPalette = RestaurantReviewsColors(
    primaryText = Color.Black,
    primaryBackground = Color(0xFFF2F2F7),
    primaryContainer = Color.White,
    secondaryText = Color(0xFF7F7F7F),
)

internal val LocalRestaurantReviewsColor =
    staticCompositionLocalOf<RestaurantReviewsColors> { error("No default implementation for colors") }