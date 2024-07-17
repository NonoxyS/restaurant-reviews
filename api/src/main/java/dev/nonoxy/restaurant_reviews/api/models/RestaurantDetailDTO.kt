package dev.nonoxy.restaurant_reviews.api.models

import kotlinx.serialization.SerialName

class RestaurantDetailDTO(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("detailedInfo") val detailedInfo: String?,
    @SerialName("photos") val photoUrls: List<String>,
    @SerialName("rate") val rate: Float?,
    @SerialName("averageCheck") val averageCheck: List<Float>,
    @SerialName("cuisines") val cuisines: List<String>,
    @SerialName("isFavorite") val isFavorite: Boolean
)