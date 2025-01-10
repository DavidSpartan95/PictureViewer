package com.davidspartan.pictureviewer.view.screenA

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.davidspartan.pictureviewer.view.ScreenB

@Composable
fun ScreenA(navController: NavHostController) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "This is my app!",
        )
        Button(onClick = {
            navController.navigate(ScreenB(name = "David", age = 21))
        }) {
            Text(
                text = "Go to Screen B",
            )
        }
    }
}