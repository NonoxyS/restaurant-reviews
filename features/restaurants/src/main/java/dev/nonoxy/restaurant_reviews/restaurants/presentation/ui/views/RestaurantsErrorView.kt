package dev.nonoxy.restaurant_reviews.restaurants.presentation.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.nonoxy.restaurant_reviews.restaurants.R
import dev.nonoxy.restaurant_reviews.restaurants.presentation.models.RestaurantsEvent
import dev.nonoxy.restaurant_reviews.theme.ui.theme.RestaurantReviewsTheme

@Composable
internal fun RestaurantsErrorView(
    eventHandler: (RestaurantsEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(42.dp)
        ) {
            Text(
                text = stringResource(R.string.error_happen),
                style = RestaurantReviewsTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center
            )

            TextButton(
                onClick = { eventHandler(RestaurantsEvent.FetchData) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = RestaurantReviewsTheme.colors.primaryIcon,
                    contentColor = RestaurantReviewsTheme.colors.iconText
                ),
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 10.dp)
            ) {
                Text(
                    text = stringResource(R.string.reload),
                    style = RestaurantReviewsTheme.typography.bodyMedium,
                    maxLines = 1,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview
@Composable
private fun CitiesError_Preview() {
    RestaurantReviewsTheme {
        RestaurantsErrorView(eventHandler = {})
    }
}