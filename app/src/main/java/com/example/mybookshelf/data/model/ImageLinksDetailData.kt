package com.example.mybookshelf.data.model

import com.google.gson.annotations.SerializedName

data class ImageLinksDetailData(
    @SerializedName("smallThumbnail") val smallThumbnail: String? = null,
    @SerializedName("thumbnail") val thumbnail: String? = null,
    @SerializedName("small") val small: String? = null,
    @SerializedName("medium") val medium: String? = null,
    @SerializedName("large") val large: String? = null,
    @SerializedName("extraLarge") val extraLarge: String? = null
)