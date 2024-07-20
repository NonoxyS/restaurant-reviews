package dev.nonoxy.restaurant_reviews.restaurants.presentation.ui

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import dev.nonoxy.restaurant_reviews.restaurants.R
import dev.nonoxy.restaurant_reviews.restaurants.presentation.RestaurantsViewModel
import dev.nonoxy.restaurant_reviews.restaurants.presentation.models.RestaurantsAction
import dev.nonoxy.restaurant_reviews.restaurants.presentation.models.RestaurantsViewState
import dev.nonoxy.restaurant_reviews.restaurants.presentation.ui.views.RestaurantsErrorView
import dev.nonoxy.restaurant_reviews.restaurants.presentation.ui.views.RestaurantsLoadingView

@Composable
fun RestaurantsScreen(
    restaurantsViewModel: RestaurantsViewModel = hiltViewModel(),
    navigateToRestaurantDetail: (Int) -> Unit
) {
    val viewState by restaurantsViewModel.viewStates().collectAsState()
    val viewAction by restaurantsViewModel.viewActions().collectAsState(initial = null)

    when (val currentState = viewState) {
        is RestaurantsViewState.Display -> RestaurantsView(
            viewState = currentState,
            eventHandler = restaurantsViewModel::obtainEvent
        )

        RestaurantsViewState.Error -> RestaurantsErrorView(eventHandler = restaurantsViewModel::obtainEvent)
        RestaurantsViewState.Loading -> RestaurantsLoadingView()
    }

    when (val currentAction = viewAction) {
        RestaurantsAction.FailedToUpdateFavoriteStatus -> {
            Toast.makeText(
                LocalContext.current,
                stringResource(R.string.error_happen) + "\n" +
                        stringResource(R.string.check_ur_internet_connection),
                Toast.LENGTH_SHORT
            ).show()
            restaurantsViewModel.clearAction()
        }

        is RestaurantsAction.NavigateToRestaurantDetail -> {
            navigateToRestaurantDetail(currentAction.restaurantId)
            restaurantsViewModel.clearAction()
        }

        else -> {}
    }
}