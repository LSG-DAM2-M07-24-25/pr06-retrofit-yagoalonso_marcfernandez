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
import androidx.navigation.compose.rememberNavController
import com.example.gotapp.navigation.BottomNavItem
import com.example.gotapp.ui.theme.GotBlack
import com.example.gotapp.ui.theme.GotGold
import com.example.gotapp.view.AppNavHost

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
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavHost(modifier = Modifier, navController = navController)
                }
            }
        }
    }
}