package dev.nonoxy.restaurant_reviews.theme.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dev.nonoxy.restaurant_reviews.theme.R

data class RestaurantReviewsTypography(
    val titleLarge: TextStyle,
    val titleMedium: TextStyle,
    val titleSmall: TextStyle,
    val bodyLarge: TextStyle,
    val bodyMedium: TextStyle,
    val bodySmall: TextStyle,
)

val SF_PRO = FontFamily(
    Font(R.font.sf_pro_regular, FontWeight.Normal),
    Font(R.font.sf_pro_medium, FontWeight.Medium),
    Font(R.font.sf_pro_semibold, FontWeight.SemiBold),
    Font(R.font.sf_pro_bold, FontWeight.Bold),
)

internal val Typography = RestaurantReviewsTypography(
    titleLarge = TextStyle(
        fontFamily = SF_PRO,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        letterSpacing = 0.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = SF_PRO,
        fontWeight = FontWeight.Medium,
        fontSize = 17.sp,
        letterSpacing = 0.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = FontFamily.Default, // Roboto
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        letterSpacing = (-0.41).sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = SF_PRO,
        fontWeight = FontWeight.Bold,
        fontSize = 17.sp,
        letterSpacing = 0.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = SF_PRO,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        letterSpacing = 0.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = SF_PRO,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        letterSpacing = (-0.41).sp
    )
)

val LocalRestaurantReviewsTypography =
    staticCompositionLocalOf<RestaurantReviewsTypography> { error("No default implementation for fonts") }