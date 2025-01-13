package com.davidspartan.pictureviewer.model.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Image::class],
    version = 1
)

abstract class ImagesDatabase: RoomDatabase() {

    abstract val dao: ImageDao

}