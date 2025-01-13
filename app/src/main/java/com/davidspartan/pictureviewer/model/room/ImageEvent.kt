package com.davidspartan.pictureviewer.model.room

sealed interface ImageEvent {
    object SaveImage: ImageEvent
    data class SetFave(val favorite: Boolean): ImageEvent
    object ShowDialog: ImageEvent
    object HideDialog: ImageEvent
    data class DeleteImage(val image: Image): ImageEvent

}