package com.davidspartan.pictureviewer.view.screenA

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.davidspartan.pictureviewer.model.Realm.ImageData
import com.davidspartan.pictureviewer.model.saveImageToInternalStorage
import com.davidspartan.pictureviewer.view.ScreenB
import com.davidspartan.pictureviewer.viewmodel.ImageViewModel

@Composable
fun ScreenA(
    navController: NavHostController,
) {

    // Get the ImageViewModel instance
    val imageViewModel: ImageViewModel = viewModel()

    val context = LocalContext.current
    var selectedImageUris by remember { mutableStateOf<List<Uri>>(emptyList()) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents(),
        onResult = { uris: List<Uri> ->
            val savedPaths = uris.mapNotNull { saveImageToInternalStorage(context, it) }
            // Save paths to Realm
            savedPaths.forEach { path ->
                val imageData = ImageData().apply {
                    name = path
                }
                imageViewModel.createImageList(imageData)
            }
        }
    )

    // Observe the state from ImageViewModel
    val Images by imageViewModel.images.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            imageViewModel.deleteAllImages()
        }) {
            Text(
                text = "DELETE ALL",
            )
        }

        Button(onClick = { launcher.launch("image/*") }) {
            Text(text = "Select Images")
        }

        // Display the selected images in a horizontal scrollable row
        if (Images.isNotEmpty()) {

            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .padding(top = 16.dp)
            ) {
                Images.forEach { uri ->
                    Image(
                        painter = rememberAsyncImagePainter(model = Uri.parse(uri.name)),
                        contentDescription = "Selected Image",
                        modifier = Modifier
                            .size(100.dp)
                            .padding(8.dp)
                    )
                }
            }
        }


        Button(onClick = {
            navController.navigate(ScreenB(name = "David", age = 21))
        }) {
            Text(
                text = "Go to Screen B",
            )
        }

    }
}

