package com.example.gotapp.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.gotapp.navigation.Routes

@Composable
fun AppNavHost(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.CharacterList.route,
        modifier = modifier
    ) {
        composable(Routes.CharacterList.route) {
            MyRecyclerView(modifier = modifier, navController = navController)
        }
        composable(
            route = Routes.DetailScreen.route,
            arguments = listOf(
                navArgument("characterId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getString("characterId").orEmpty()
            DetailScreen(
                navController = navController,
                characterId = characterId,
                modifier = modifier
            )
        }
    }
}