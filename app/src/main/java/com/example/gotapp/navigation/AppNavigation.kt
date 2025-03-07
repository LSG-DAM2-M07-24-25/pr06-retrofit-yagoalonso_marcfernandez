package com.example.gotapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.gotapp.viewmodel.APIViewModel
import com.example.gotapp.viewmodel.HousesViewModel

@Composable
fun AppNavigation(
    apiViewModel: APIViewModel,
    housesViewModel: HousesViewModel
) {
    val navController = rememberNavController()
    NavGraph(
        navController = navController,
        apiViewModel = apiViewModel,
        housesViewModel = housesViewModel
    )
} 