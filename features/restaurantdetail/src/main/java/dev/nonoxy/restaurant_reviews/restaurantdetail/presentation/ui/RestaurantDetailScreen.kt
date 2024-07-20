package dev.nonoxy.restaurant_reviews.restaurantdetail.presentation.ui

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import dev.nonoxy.restaurant_reviews.common.LocalNavHost
import dev.nonoxy.restaurant_reviews.restaurantdetail.R
import dev.nonoxy.restaurant_reviews.restaurantdetail.presentation.RestaurantDetailViewModel
import dev.nonoxy.restaurant_reviews.restaurantdetail.presentation.models.RestaurantDetailAction
import dev.nonoxy.restaurant_reviews.restaurantdetail.presentation.models.RestaurantDetailViewState
import dev.nonoxy.restaurant_reviews.restaurantdetail.presentation.ui.views.RestaurantDetailErrorView
import dev.nonoxy.restaurant_reviews.restaurantdetail.presentation.ui.views.RestaurantDetailLoadingView

@Composable
fun RestaurantDetailScreen(
    restaurantDetailViewModel: RestaurantDetailViewModel = hiltViewModel(),
) {
    val viewState by restaurantDetailViewModel.viewStates().collectAsState()
    val viewAction by restaurantDetailViewModel.viewActions().collectAsState(initial = null)

    when (val currentState = viewState) {
        RestaurantDetailViewState.Error -> RestaurantDetailErrorView(
            eventHandler = restaurantDetailViewModel::obtainEvent
        )

        RestaurantDetailViewState.Loading -> RestaurantDetailLoadingView()
        is RestaurantDetailViewState.Display -> RestaurantDetailView(
            viewState = currentState,
            eventHandler = restaurantDetailViewModel::obtainEvent
        )
    }

    when (viewAction) {
        RestaurantDetailAction.NavigateBack -> {
            LocalNavHost.current.navigateUp()
            restaurantDetailViewModel.clearAction()
        }

        RestaurantDetailAction.FailedToUpdateFavoriteStatus -> {
            Toast.makeText(
                LocalContext.current,
                stringResource(R.string.error_happen) + "\n" +
                        stringResource(R.string.check_ur_internet_connection),
                Toast.LENGTH_SHORT
            ).show()
            restaurantDetailViewModel.clearAction()
        }
        else -> {}
    }
}