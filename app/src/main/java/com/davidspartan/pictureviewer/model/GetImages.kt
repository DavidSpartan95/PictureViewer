package com.davidspartan.pictureviewer.model

import android.content.Context
import android.net.Uri
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream


fun saveImageToInternalStorage(context: Context, uri: Uri): String? {
    return try {
        val contentResolver = context.contentResolver
        val inputStream: InputStream? = contentResolver.openInputStream(uri)

        val fileName = "${System.currentTimeMillis()}.jpg"
        val file = File(context.filesDir, fileName)

        inputStream?.use { input ->
            FileOutputStream(file).use { output ->
                input.copyTo(output)
            }
        }

        file.absolutePath // Return the saved file path
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}