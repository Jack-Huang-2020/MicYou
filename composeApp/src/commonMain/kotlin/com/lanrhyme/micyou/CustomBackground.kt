package com.lanrhyme.micyou

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

@Composable
fun CustomBackground(
    settings: BackgroundSettings,
    modifier: Modifier = Modifier
) {
    if (!settings.hasCustomBackground) {
        return
    }
    
    val imageBitmap = remember(settings.imagePath) {
        loadImageBitmap(settings.imagePath)
    }
    
    if (imageBitmap != null) {
        Box(modifier = modifier) {
            Image(
                bitmap = imageBitmap,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .blur(radius = settings.blurRadius.dp),
                contentScale = ContentScale.Crop
            )
            
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 1f - settings.brightness))
            )
        }
    }
}

@Composable
fun CardWithOpacity(
    opacity: Float,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(modifier = modifier.graphicsLayer { alpha = opacity }) {
        content()
    }
}

expect fun loadImageBitmap(path: String): ImageBitmap?
