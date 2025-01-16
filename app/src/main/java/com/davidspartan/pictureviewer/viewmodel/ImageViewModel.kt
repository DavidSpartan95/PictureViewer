package com.davidspartan.pictureviewer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidspartan.pictureviewer.model.Realm.ImageData
import com.davidspartan.pictureviewer.model.Realm.MyApp
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ImageViewModel: ViewModel() {

    private val realm = MyApp.realm

    val images = realm
        .query<ImageData>()
        .asFlow()
        .map { results ->
            results.list.toList()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            emptyList()
        )

    fun createImageList(Image: ImageData){

        viewModelScope.launch {
            realm.write {
                    copyToRealm(Image, updatePolicy = UpdatePolicy.ALL)
            }
        }
    }
    fun deleteAllImages() {
        viewModelScope.launch {
            realm.write {
                // Delete all ImageData objects
                val allImages = query<ImageData>().find()
                delete(allImages)
            }
        }
    }
}

