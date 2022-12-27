package com.example.mybookshelf.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ImageLinks(
   @SerializedName("thumbnail") val thumbnail: String? = null
)
