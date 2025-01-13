package com.davidspartan.pictureviewer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidspartan.pictureviewer.model.room.ImageDao
import com.davidspartan.pictureviewer.model.room.ImageEvent
import com.davidspartan.pictureviewer.model.room.ImageState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ImageViewModel(
    private val dao: ImageDao
): ViewModel()  {

    private val _images = dao.getAllImages()
    private val _state = MutableStateFlow(ImageState())
    val state = combine(_state, _images) { state, images ->
        state.copy(
            images = images
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ImageState())

    fun onEvent(event: ImageEvent) {
        when(event){
            is ImageEvent.DeleteImage -> {
                viewModelScope.launch {
                    dao.deleteImage(event.image)
                }
            }
            ImageEvent.SaveImage -> {
                val image = _state.value.image
                viewModelScope.launch {
                    dao.insertImage(image)
                }
            }
            is ImageEvent.SetFave -> {
                _state.update {
                    it.copy(
                        favorite = event.favorite
                    )
                }
            }
            ImageEvent.ShowDialog -> TODO()
            ImageEvent.HideDialog -> TODO()
        }
    }

}