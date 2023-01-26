package com.example.mybookshelf.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ImageLinksDto(
   @SerializedName("thumbnail") val thumbnail: String? = null,
   @SerializedName("small") val small: String? = null
)
