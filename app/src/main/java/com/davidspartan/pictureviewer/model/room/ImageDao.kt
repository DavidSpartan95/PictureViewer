package com.davidspartan.pictureviewer.model.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageDao {

    @Upsert
    suspend fun insertImage(image: Image)

    @Delete
    suspend fun deleteImage(image: Image)

    @Query("SELECT * FROM images ORDER BY id")
    fun getAllImages(): Flow<List<Image>>

}