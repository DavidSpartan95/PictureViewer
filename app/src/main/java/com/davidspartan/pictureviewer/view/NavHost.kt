package com.davidspartan.pictureviewer.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.davidspartan.pictureviewer.view.Slideshow.SlideshowScreen
import com.davidspartan.pictureviewer.view.screenA.ScreenA
import com.davidspartan.pictureviewer.view.screenB.ScreenB
import com.davidspartan.pictureviewer.viewmodel.ImageViewModel
import kotlinx.serialization.Serializable


@Composable
fun MyNavHost (
    navController: NavHostController,
    viewModel: ImageViewModel
) {
    NavHost(navController, startDestination = ScreenA) {
        composable<ScreenA> {
            ScreenA(
                navController,
                viewModel
            )
        }
        composable<ScreenB> {

            ScreenB(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable<SlideShowScreen> {

            SlideshowScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}
@Serializable
object SlideShowScreen

@Serializable
object ScreenA

@Serializable
object ScreenB


