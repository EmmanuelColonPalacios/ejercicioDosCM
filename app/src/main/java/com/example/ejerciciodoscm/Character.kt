package com.example.ejerciciodoscm

import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("affiliation") val affiliation: String?,
    @SerializedName("gender") val gender: String?,
    @SerializedName("profession") val profession: String?,
    @SerializedName("position") val position: String?,
    @SerializedName("imageUrl") val imageUrl: String?,
    @SerializedName("element") val element: String?
)
