package com.davidspartan.pictureviewer.view.screenA

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.davidspartan.pictureviewer.view.OptionButton
import com.davidspartan.pictureviewer.view.ScreenB
import com.davidspartan.pictureviewer.view.SlideShowScreen
import com.davidspartan.pictureviewer.viewmodel.ImageViewModel


@Composable
fun ScreenA(
    navController: NavHostController,
    viewModel: ImageViewModel
) {

    val images by viewModel.images.collectAsState()
    val context = androidx.compose.ui.platform.LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize().padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OptionButton(
            text = "Slideshow",
            onClick = {
                if (images.isNotEmpty()){
                    navController.navigate(SlideShowScreen)
                }else{
                    Toast.makeText(context, "No Pictures stored", Toast.LENGTH_SHORT).show()
                }

            }
        )
        Spacer(modifier = Modifier.padding(5.dp))
        OptionButton(
            text = "Library",
            onClick = {
                navController.navigate(ScreenB)
            }
        )
    }
}

