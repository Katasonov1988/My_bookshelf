package com.example.mybookshelf.data.model

import com.google.gson.annotations.SerializedName

data class ImageLinksData(
    @SerializedName("thumbnail") val thumbnail: String? = null
)
