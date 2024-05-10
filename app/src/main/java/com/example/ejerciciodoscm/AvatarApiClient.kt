package com.example.ejerciciodoscm

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AvatarApiClient {
    private const val BASE_URL = "https://last-airbender-api.fly.dev/"
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: AvatarApiService = retrofit.create(AvatarApiService::class.java)
}
