package dev.nonoxy.restaurant_reviews.restaurantdetail.presentation

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.nonoxy.restaurant_reviews.common.base.BaseViewModel
import dev.nonoxy.restaurant_reviews.common.eventbus.EventBus
import dev.nonoxy.restaurant_reviews.common.eventbus.EventBusController
import dev.nonoxy.restaurant_reviews.data.RequestResult
import dev.nonoxy.restaurant_reviews.restaurantdetail.domain.usecases.AddToFavoritesUseCase
import dev.nonoxy.restaurant_reviews.restaurantdetail.domain.usecases.GetRestaurantDetailUseCase
import dev.nonoxy.restaurant_reviews.restaurantdetail.domain.usecases.RemoveFromFavoritesUseCase
import dev.nonoxy.restaurant_reviews.restaurantdetail.presentation.models.RestaurantDetailAction
import dev.nonoxy.restaurant_reviews.restaurantdetail.presentation.models.RestaurantDetailEvent
import dev.nonoxy.restaurant_reviews.restaurantdetail.presentation.models.RestaurantDetailViewState
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantDetailViewModel @Inject constructor(
    private val getRestaurantDetailUseCase: GetRestaurantDetailUseCase,
    private val addToFavoritesUseCase: AddToFavoritesUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase,
    private val eventBusController: EventBusController,
) : BaseViewModel<RestaurantDetailViewState, RestaurantDetailAction, RestaurantDetailEvent>(
    initialState = RestaurantDetailViewState.Loading
) {
    override fun obtainEvent(viewEvent: RestaurantDetailEvent) {
        when (viewEvent) {
            is RestaurantDetailEvent.FetchData -> fetchData(viewEvent.restaurantId)
            is RestaurantDetailEvent.FavoriteIconClicked -> {
                if (viewEvent.restaurant.isFavorite) removeFromFavorites()
                else addToFavorites()
            }

            RestaurantDetailEvent.NavigateBackClicked -> viewAction =
                RestaurantDetailAction.NavigateBack
        }
    }

    private fun fetchData(restaurantId: Int) {
        viewModelScope.launch {
            getRestaurantDetailUseCase.execute(restaurantId = restaurantId).collect { result ->
                when (result) {
                    is RequestResult.Error -> viewState = RestaurantDetailViewState.Error(restaurantId)
                    is RequestResult.InProgress -> viewState = RestaurantDetailViewState.Loading
                    is RequestResult.Success -> {
                        viewState =
                            RestaurantDetailViewState.Display(restaurantDetail = result.data)
                    }
                }
            }
        }
    }

    private fun addToFavorites() {
        viewModelScope.launch {
            when (val currentState = viewState) {
                RestaurantDetailViewState.Loading -> viewState = currentState
                is RestaurantDetailViewState.Error -> viewState = currentState
                is RestaurantDetailViewState.Display -> {
                    viewState = currentState.copy(
                        restaurantDetail = currentState.restaurantDetail.copy(isFavorite = true)
                    )

                    addToFavoritesUseCase.execute(
                        restaurantId = currentState.restaurantDetail.id
                    ).collect { result ->
                        when (result) {
                            is RequestResult.InProgress -> Unit
                            is RequestResult.Success -> {
                                eventBusController.publishEvent(
                                    EventBus.ChangeRestaurantFavoriteStatus(
                                        restaurantId = currentState.restaurantDetail.id,
                                        isFavorite = true
                                    )
                                )
                            }

                            is RequestResult.Error -> {
                                // В случае ошибки возвращаем предыдущее состояние
                                // И кидаем Action для тоста
                                viewState = currentState
                                viewAction = RestaurantDetailAction.FailedToUpdateFavoriteStatus
                            }
                        }
                    }
                }
            }
        }
    }

    private fun removeFromFavorites() {
        viewModelScope.launch {
            when (val currentState = viewState) {
                is RestaurantDetailViewState.Error -> viewState = currentState
                RestaurantDetailViewState.Loading -> viewState = currentState
                is RestaurantDetailViewState.Display -> {
                    viewState = currentState.copy(
                        restaurantDetail = currentState.restaurantDetail.copy(isFavorite = false)
                    )

                    removeFromFavoritesUseCase.execute(
                        restaurantId = currentState.restaurantDetail.id
                    ).collect { result ->
                        when (result) {
                            is RequestResult.InProgress -> Unit
                            is RequestResult.Success -> {
                                eventBusController.publishEvent(
                                    EventBus.ChangeRestaurantFavoriteStatus(
                                        restaurantId = currentState.restaurantDetail.id,
                                        isFavorite = false
                                    )
                                )
                            }

                            is RequestResult.Error -> {
                                // В случае ошибки возвращаем предыдущее состояние
                                // И кидаем Action для тоста
                                viewState = currentState
                                viewAction = RestaurantDetailAction.FailedToUpdateFavoriteStatus
                            }
                        }
                    }
                }
            }
        }
    }
}