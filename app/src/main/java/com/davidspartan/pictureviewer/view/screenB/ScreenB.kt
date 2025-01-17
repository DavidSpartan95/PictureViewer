package com.davidspartan.pictureviewer.view.screenB

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.davidspartan.pictureviewer.model.Realm.ImageData
import com.davidspartan.pictureviewer.model.saveImageToInternalStorage
import com.davidspartan.pictureviewer.view.OptionButton
import com.davidspartan.pictureviewer.viewmodel.ImageViewModel

@Composable
fun ScreenB(
    navController: NavHostController,
    viewModel: ImageViewModel
) {

    val images by viewModel.images.collectAsState()

    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents(),
        onResult = { uris: List<Uri> ->
            val savedPaths = uris.mapNotNull { saveImageToInternalStorage(context, it) }
            // Save paths to Realm
            savedPaths.forEach { path ->
                val imageData = ImageData().apply {
                    name = path
                }
                viewModel.createImageList(imageData)
            }
        }
    )


    Column(
        modifier = Modifier
            .fillMaxSize() // Make sure it fills the screen
            .statusBarsPadding(), // Add padding for status bar (safe area)
        horizontalAlignment = Alignment.CenterHorizontally, // Center horizontally
        verticalArrangement = Arrangement.Top // Center vertically
    ) {
        OptionButton(
            text = "BACK TO MENU",
            onClick = {
                navController.popBackStack()
            }
        )

        Row(Modifier.padding(5.dp)) {
            OptionButton(
                text = "ADD IMAGES",
                onClick = {
                    launcher.launch("image/*")
                }
            )
            Spacer(modifier = Modifier.padding(5.dp))
            OptionButton(
                text = "DELETE ALL",
                onClick = {
                    viewModel.deleteAllImages()
                }
            )

        }

        ImageGrid(images)
    }

}
@Composable
fun ImageGrid(images: List<ImageData>) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 100.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        items(images) { imageData ->
            Image(
                painter = rememberAsyncImagePainter(model = Uri.parse(imageData.name)),
                contentDescription = "Grid Image",
                modifier = Modifier
                    .padding(4.dp)
                    .size(100.dp),
                contentScale = ContentScale.Crop // Ensures the image is cropped to fill the square size
            )
        }
    }
}