package com.example.mybookshelf.model

import com.google.gson.annotations.SerializedName

data class ImageLinksDetail(
    @field:SerializedName("small") val small: String,
    @field:SerializedName("medium") val medium: String,
    @field:SerializedName("large") val large: String,
    @field:SerializedName("extraLa,rge") val extraLarge: String
)

