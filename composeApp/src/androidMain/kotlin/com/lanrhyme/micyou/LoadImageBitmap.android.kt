package com.lanrhyme.micyou

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap

actual fun loadImageBitmap(path: String): ImageBitmap? {
    return try {
        val context = AndroidContext.context ?: return null
        val uri = Uri.parse(path)
        
        val inputStream = context.contentResolver.openInputStream(uri)
            ?: return null
        
        val bitmap = BitmapFactory.decodeStream(inputStream)
        inputStream.close()
        
        bitmap?.asImageBitmap()
    } catch (e: Exception) {
        Logger.e("BackgroundImage", "Failed to load image: $path", e)
        null
    }
}
