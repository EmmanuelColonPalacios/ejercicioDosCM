package com.example.ejerciciodoscm

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AvatarApiService {
    @GET("api/v1/characters")
    fun getCharacters(): Call<List<Character>>

    @GET("api/v1/characters/{id}")
    fun getCharacterDetails(@Path("id") id: String): Call<Character>
}
