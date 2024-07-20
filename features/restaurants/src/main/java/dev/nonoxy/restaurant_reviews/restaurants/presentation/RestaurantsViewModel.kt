package dev.nonoxy.restaurant_reviews.restaurants.presentation

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.nonoxy.restaurant_reviews.common.base.BaseViewModel
import dev.nonoxy.restaurant_reviews.common.eventbus.EventBus
import dev.nonoxy.restaurant_reviews.common.eventbus.EventBusController
import dev.nonoxy.restaurant_reviews.data.RequestResult
import dev.nonoxy.restaurant_reviews.restaurants.domain.models.RestaurantUI
import dev.nonoxy.restaurant_reviews.restaurants.domain.usecases.AddToFavoritesUseCase
import dev.nonoxy.restaurant_reviews.restaurants.domain.usecases.GetRestaurantsUseCase
import dev.nonoxy.restaurant_reviews.restaurants.domain.usecases.RemoveFromFavoritesUseCase
import dev.nonoxy.restaurant_reviews.restaurants.presentation.models.RestaurantsAction
import dev.nonoxy.restaurant_reviews.restaurants.presentation.models.RestaurantsEvent
import dev.nonoxy.restaurant_reviews.restaurants.presentation.models.RestaurantsViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RestaurantsViewModel @Inject constructor(
    private val getRestaurantsUseCase: GetRestaurantsUseCase,
    private val addToFavoritesUseCase: AddToFavoritesUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase,
    private val eventBusController: EventBusController,
) : BaseViewModel<RestaurantsViewState, RestaurantsAction, RestaurantsEvent>(initialState = RestaurantsViewState.Loading) {

    init {
        fetchData()

        eventBusController.eventBus.onEach { event ->
            when (event) {
                is EventBus.ChangeRestaurantFavoriteStatus -> {
                    when (val currentState = viewState) {
                        RestaurantsViewState.Loading -> viewState = currentState
                        RestaurantsViewState.Error -> viewState = currentState
                        is RestaurantsViewState.Display -> {
                            val favoriteCountDifference = if (event.isFavorite) 1 else -1

                            val updatedFavoriteRestaurants: MutableList<RestaurantUI> =
                                currentState.restaurants.toMutableList()

                            val updatedRestaurants = withContext(Dispatchers.Default) {
                                currentState.restaurants.map { restaurant ->
                                    if (restaurant.id == event.restaurantId) {
                                        val updatedRestaurant =
                                            restaurant.copy(isFavorite = event.isFavorite)
                                        updatedFavoriteRestaurants.add(updatedRestaurant)
                                        updatedRestaurant
                                    } else restaurant
                                }
                            }
                            viewState = currentState.copy(
                                favoriteRestaurantsCount = currentState.favoriteRestaurantsCount + favoriteCountDifference,
                                restaurants = updatedRestaurants,
                                favoriteRestaurants = updatedFavoriteRestaurants
                            )
                        }
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    override fun obtainEvent(viewEvent: RestaurantsEvent) {
        when (viewEvent) {
            RestaurantsEvent.FetchData -> fetchData()
            RestaurantsEvent.ToggleFavoriteFilter -> toggleFavoriteFilter()
            is RestaurantsEvent.FavoriteIconClicked -> {
                if (viewEvent.restaurant.isFavorite) removeFromFavorites(viewEvent.restaurant.id)
                else addToFavorites(viewEvent.restaurant.id)
            }

            is RestaurantsEvent.RestaurantCardClicked -> viewAction =
                RestaurantsAction.NavigateToRestaurantDetail(viewEvent.restaurantId)
        }
    }

    private fun fetchData() {
        viewModelScope.launch {
            getRestaurantsUseCase.execute().collect { result ->
                when (result) {
                    is RequestResult.Error -> viewState = RestaurantsViewState.Error
                    is RequestResult.InProgress -> viewState = RestaurantsViewState.Loading
                    is RequestResult.Success -> {
                        viewState = withContext(Dispatchers.Default) {
                            RestaurantsViewState.Display(
                                favoriteRestaurantsCount = result.data.count { it.isFavorite },
                                inFavoriteRestaurantsMode = false,
                                restaurants = result.data,
                                favoriteRestaurants = result.data.filter { it.isFavorite }
                            )
                        }
                    }
                }
            }
        }
    }

    private fun toggleFavoriteFilter() {
        viewModelScope.launch {
            viewState = when (val currentState = viewState) {
                RestaurantsViewState.Error -> currentState
                RestaurantsViewState.Loading -> currentState
                is RestaurantsViewState.Display -> {
                    val needFilterFavorites = !currentState.inFavoriteRestaurantsMode
                    val filteredRestaurants = if (needFilterFavorites) {
                        withContext(Dispatchers.Default) {
                            currentState.restaurants.filter { it.isFavorite }
                        }
                    } else currentState.restaurants

                    currentState.copy(
                        favoriteRestaurants = filteredRestaurants,
                        inFavoriteRestaurantsMode = needFilterFavorites
                    )
                }
            }
        }
    }

    private fun addToFavorites(restaurantId: Int) {
        viewModelScope.launch {
            when (val currentState = viewState) {
                is RestaurantsViewState.Error -> viewState = currentState
                is RestaurantsViewState.Loading -> viewState = currentState

                is RestaurantsViewState.Display -> {
                    val updatedFavoriteRestaurants: MutableList<RestaurantUI> =
                        currentState.favoriteRestaurants.toMutableList()

                    val updatedRestaurants = withContext(Dispatchers.Default) {
                        currentState.restaurants.map { restaurant ->
                            if (restaurant.id == restaurantId) {
                                val updatedRestaurant = restaurant.copy(isFavorite = true)
                                updatedFavoriteRestaurants.add(updatedRestaurant)
                                updatedRestaurant
                            } else restaurant
                        }
                    }

                    viewState = currentState.copy(
                        favoriteRestaurantsCount = currentState.favoriteRestaurantsCount + 1,
                        restaurants = updatedRestaurants,
                        favoriteRestaurants = updatedFavoriteRestaurants
                    )

                    addToFavoritesUseCase.execute(restaurantId = restaurantId).collect { result ->
                        when (result) {
                            is RequestResult.InProgress -> Unit
                            is RequestResult.Success -> Unit
                            is RequestResult.Error -> {
                                // В случае ошибки возвращаем предыдущее состояние
                                // И кидаем Action для тоста
                                viewState = currentState
                                viewAction = RestaurantsAction.FailedToUpdateFavoriteStatus
                            }
                        }
                    }
                }
            }
        }
    }

    private fun removeFromFavorites(restaurantId: Int) {
        viewModelScope.launch {
            when (val currentState = viewState) {
                is RestaurantsViewState.Error -> viewState = currentState
                is RestaurantsViewState.Loading -> viewState = currentState

                is RestaurantsViewState.Display -> {
                    val updatedFavoriteRestaurants: MutableList<RestaurantUI> =
                        currentState.favoriteRestaurants.toMutableList()

                    val updatedRestaurants = withContext(Dispatchers.Default) {
                        currentState.restaurants.map { restaurant ->
                            if (restaurant.id == restaurantId) {
                                val updatedRestaurant = restaurant.copy(isFavorite = false)
                                updatedFavoriteRestaurants.remove(restaurant)
                                updatedRestaurant
                            } else restaurant
                        }
                    }

                    viewState = currentState.copy(
                        favoriteRestaurantsCount = currentState.favoriteRestaurantsCount - 1,
                        restaurants = updatedRestaurants,
                        favoriteRestaurants = updatedFavoriteRestaurants
                    )

                    removeFromFavoritesUseCase.execute(restaurantId = restaurantId)
                        .collect { result ->
                            when (result) {
                                is RequestResult.InProgress -> Unit
                                is RequestResult.Success -> Unit
                                is RequestResult.Error -> {
                                    // В случае ошибки возвращаем предыдущее состояние
                                    // И кидаем Action для тоста
                                    viewState = currentState
                                    viewAction = RestaurantsAction.FailedToUpdateFavoriteStatus
                                }
                            }
                        }
                }
            }
        }
    }
}
