package dev.nonoxy.restaurant_reviews.restaurantdetail.presentation.ui.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.imageLoader

@Composable
internal fun RestaurantDetailPagerView(
    photosUrl: List<String>
) {
    val pagerState = rememberPagerState {
        photosUrl.size
    }
    if (photosUrl.size > 1) {
        HorizontalPager(
            state = pagerState,
            pageSize = PageSize.Fixed(400.dp),
            pageSpacing = 8.dp
        ) { page ->
            AsyncImage(
                model = photosUrl[page],
                contentDescription = null,
                contentScale = ContentScale.Crop,
                imageLoader = LocalContext.current.imageLoader,
                modifier = Modifier
                    .height(250.dp)
                    .clip(RoundedCornerShape(26.dp))
            )
        }
    } else {
        AsyncImage(
            model = photosUrl.firstOrNull(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            imageLoader = LocalContext.current.imageLoader,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(26.dp))
        )
    }
}