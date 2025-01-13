package com.davidspartan.pictureviewer.model.room

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images")
data class Image(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val uri: String,
    val favorite: Boolean = false
)
