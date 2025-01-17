package com.davidspartan.pictureviewer.view.Slideshow

import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import coil.Coil
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.davidspartan.pictureviewer.viewmodel.ImageViewModel
import kotlinx.coroutines.delay

@Composable
fun SlideshowScreen(
    navController: NavHostController,
    viewModel: ImageViewModel
) {

    val images by viewModel.images.collectAsState()
    var currentIndex by remember { mutableIntStateOf(0) } // Index to track the current image
    var isVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current
    // Preload all images using Coil
    LaunchedEffect(images) {
        images.forEach { image ->
            val request = ImageRequest.Builder(context)
                .data(Uri.parse(image.name))
                .build()
            Coil.imageLoader(context).enqueue(request)
        }
    }

    LaunchedEffect(currentIndex) {
        isVisible = true
        delay(3000)
        isVisible = false
        delay(500) // Short delay before switching to the next image
        currentIndex = (currentIndex + 1) % images.size
    }

    // Display content
    Box(
        modifier = Modifier.fillMaxSize()
            .background(Color.Black)
            .clickable { navController.popBackStack() },
        contentAlignment = Alignment.Center,

    ) {

        if (images.isNotEmpty()) {
            images.forEachIndexed{ index, image ->
                // Animated fade-in and fade-out transition
                AnimatedVisibility(
                    visible = isVisible && currentIndex == index,
                    enter = fadeIn(animationSpec = tween(durationMillis = 1000)),
                    exit = fadeOut(animationSpec = tween(durationMillis = 1000))
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            model = Uri.parse(image.name)
                        ),
                        contentDescription = "Slideshow Image",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit // Ensure image fits the screen
                    )
                }

            }

        } else {
            Text("No images found in the Library")
            //CircularProgressIndicator() // Show loading indicator while waiting for image
        }
    }
}

