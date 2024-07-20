package dev.nonoxy.restaurant_reviews.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class RestaurantDTO(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("photo") val photoUrl: String,
    @SerialName("rate") val rate: Float?,
    @SerialName("averageCheck") val averageCheck: List<Float>,
    @SerialName("cuisines") val cuisines: List<String>,
    @SerialName("isFavorite") val isFavorite: Boolean
)

@Serializable
class RestaurantsDTO(
    @SerialName("data") val restaurants: List<RestaurantDTO>
)