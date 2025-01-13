package com.davidspartan.pictureviewer.model.room

import android.net.Uri

data class ImageState(
    val images: List<Image> = emptyList(), // This is now part of the primary constructor
    val uri: String = "",
    val isAddingImage: Boolean = false,
    val favorite: Boolean = false
)
