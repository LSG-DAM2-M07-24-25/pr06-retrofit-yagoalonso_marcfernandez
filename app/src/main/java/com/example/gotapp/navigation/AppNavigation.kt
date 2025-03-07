// Este archivo es el punto de entrada principal para la navegación de la app
package com.example.gotapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.gotapp.viewmodel.APIViewModel
import com.example.gotapp.viewmodel.HousesViewModel

@Composable
fun AppNavigation(
    apiViewModel: APIViewModel,    // ViewModel para manejar datos de la API
    housesViewModel: HousesViewModel    // ViewModel para manejar datos de las casas
) {
    // Crear un controlador de navegación que recordará el estado
    val navController = rememberNavController()
    
    // Iniciar la navegación con el NavGraph
    NavGraph(
        navController = navController,
        apiViewModel = apiViewModel,
        housesViewModel = housesViewModel
    )
} 