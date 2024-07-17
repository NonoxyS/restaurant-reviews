package dev.nonoxy.restaurant_reviews.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import dev.nonoxy.restaurant_reviews.api.models.RestaurantDTO
import dev.nonoxy.restaurant_reviews.api.models.RestaurantDetailDTO
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RestaurantReviewsApi {
    @GET("internship/organizations/category/1/organizations/")
    suspend fun getRestaurants(): Result<List<RestaurantDTO>>

    @GET("internship/organization/{id}")
    suspend fun getRestaurantById(
        @Path("id") id: Int
    ): Result<RestaurantDetailDTO>

    @POST("/internship/organization/{id}/favorite/")
    suspend fun addToFavorites(
        @Path("id") id: Int
    ): Result<Unit>

    @DELETE("/internship/organization/{id}/favorite/")
    suspend fun removeFromFavorites(
        @Path("id") id: Int
    ): Result<Unit>
}

fun RestaurantReviewsApi(
    baseUrl: String,
    apiKey: String,
    okHttpClient: OkHttpClient? = null
): RestaurantReviewsApi {
    return retrofit(baseUrl, apiKey, okHttpClient).create()
}

private fun retrofit(
    baseUrl: String,
    apiKey: String,
    okHttpClient: OkHttpClient?
): Retrofit {
    val json = Json { ignoreUnknownKeys = true }
    val jsonConverterFactory = json.asConverterFactory("application/json".toMediaType())

    val modifiedOkHttpClient = (okHttpClient?.newBuilder() ?: OkHttpClient.Builder())
        .addInterceptor(RestaurantReviewsApiInterceptor(apiKey))
        .build()

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(ResultCallAdapterFactory.create())
        .addConverterFactory(jsonConverterFactory)
        .client(modifiedOkHttpClient)
        .build()
}