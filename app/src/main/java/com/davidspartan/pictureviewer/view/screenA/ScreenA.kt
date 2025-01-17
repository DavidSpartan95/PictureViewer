package com.davidspartan.pictureviewer.view.screenA

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
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

    Column(
        modifier = Modifier.fillMaxSize().padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OptionButton(
            text = "Slideshow",
            onClick = {
                navController.navigate(SlideShowScreen)
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

