package dev.nonoxy.restaurant_reviews.api

import okhttp3.Interceptor
import okhttp3.Response

internal class RestaurantReviewsApiInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder()
                .addHeader("Authorization", "Token $apiKey")
                .build()
        )
    }
}