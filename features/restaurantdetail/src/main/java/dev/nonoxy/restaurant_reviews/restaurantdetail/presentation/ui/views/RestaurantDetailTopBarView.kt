package dev.nonoxy.restaurant_reviews.restaurantdetail.presentation.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import dev.nonoxy.restaurant_reviews.restaurantdetail.R
import dev.nonoxy.restaurant_reviews.theme.ui.theme.RestaurantReviewsTheme

@Composable
internal fun RestaurantDetailTopBarView(
    onNavigateBackClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            IconButton(
                onClick = { onNavigateBackClicked() },
                modifier = Modifier.size(24.dp).align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_back_arrow),
                    contentDescription = null,
                    tint = RestaurantReviewsTheme.colors.primaryText,
                    modifier = Modifier.size(24.dp)
                )
            }
            Text(
                text = stringResource(R.string.restaurant),
                style = RestaurantReviewsTheme.typography.titleSmall,
                color = RestaurantReviewsTheme.colors.primaryText,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Preview
@Composable
private fun RestaurantDetailTopBarView_Preview() {
    RestaurantReviewsTheme {
        RestaurantDetailTopBarView(
            onNavigateBackClicked = {}
        )
    }
}