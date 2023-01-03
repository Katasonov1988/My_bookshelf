package com.example.mybookshelf.data.model

import com.google.gson.annotations.SerializedName

data class ImageLinksDetailDto(
    @field:SerializedName("small") val small: String? = null,
    @field:SerializedName("medium") val medium: String? = null,
    @field:SerializedName("large") val large: String? = null,
    @field:SerializedName("extraLa,rge") val extraLarge: String? = null
)

