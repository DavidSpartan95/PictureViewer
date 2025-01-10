package com.davidspartan.pictureviewer.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.davidspartan.pictureviewer.view.screenA.ScreenA
import com.davidspartan.pictureviewer.view.screenB.ScreenB
import kotlinx.serialization.Serializable

@Composable
fun MyNavHost (navController: NavHostController) {
    NavHost(navController, startDestination = ScreenA) {
        composable<ScreenA> {
            ScreenA(navController)
        }
        composable<ScreenB> {
            val args = it.toRoute<ScreenB>()
            ScreenB(
                name = args.name,
                age = args.age
            )
        }
    }
}
@Serializable
object ScreenA

@Serializable
data class ScreenB(
    val name: String,
    val age: Int
)
