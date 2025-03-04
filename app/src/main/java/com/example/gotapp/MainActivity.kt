package com.example.gotapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.NavType
import com.example.gotapp.navigation.BottomNavItem
import com.example.gotapp.ui.theme.GotBlack
import com.example.gotapp.ui.theme.GotGold
import com.example.gotapp.view.*

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val items = listOf(
                BottomNavItem.Characters,
                BottomNavItem.Deaths,
                BottomNavItem.Houses
            )
            
            Scaffold(
                bottomBar = {
                    NavigationBar(
                        containerColor = GotBlack
                    ) {
                        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
                        items.forEach { item ->
                            NavigationBarItem(
                                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                                label = { Text(item.title) },
                                selected = currentRoute == item.route,
                                onClick = {
                                    navController.navigate(item.route) {
                                        popUpTo(navController.graph.startDestinationId)
                                        launchSingleTop = true
                                    }
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = GotGold,
                                    selectedTextColor = GotGold,
                                    indicatorColor = GotBlack
                                )
                            )
                        }
                    }
                }
            ) { paddingValues ->
                NavHost(
                    navController = navController,
                    startDestination = BottomNavItem.Characters.route,
                    modifier = Modifier.padding(paddingValues)
                ) {
                    composable(BottomNavItem.Characters.route) {
                        MyRecyclerView(navController = navController)
                    }
                    composable(BottomNavItem.Deaths.route) {
                        DeathView(navController = navController)
                    }
                    composable(BottomNavItem.Houses.route) {
                        HousesView(navController = navController)
                    }
                    composable(
                        route = "detail_screen/{id}",
                        arguments = listOf(navArgument("id") { type = NavType.StringType })
                    ) { backStackEntry ->
                        DetailScreen(
                            characterId = backStackEntry.arguments?.getString("id") ?: "",
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}