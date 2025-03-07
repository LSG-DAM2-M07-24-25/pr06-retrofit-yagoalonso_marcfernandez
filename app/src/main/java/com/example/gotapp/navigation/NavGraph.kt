// Este archivo define cómo se conectan todas las pantallas de la app
package com.example.gotapp.navigation

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import androidx.navigation.NavType
import com.example.gotapp.ui.theme.GotDarkGray
import com.example.gotapp.ui.theme.GotGold
import com.example.gotapp.view.compact.*
import com.example.gotapp.viewmodel.APIViewModel
import com.example.gotapp.viewmodel.HousesViewModel
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.platform.LocalConfiguration

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraph(
    navController: NavHostController,    // Controla la navegación entre pantallas
    apiViewModel: APIViewModel,          // Maneja los datos de la API
    housesViewModel: HousesViewModel     // Maneja los datos de las casas
) {
    // Obtener la ruta actual para saber en qué pantalla estamos
    val currentRoute by navController.currentBackStackEntryAsState()

    Scaffold(
        // Definir la barra de navegación inferior
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFF121212),    // Color de fondo negro
                tonalElevation = 0.dp,
                modifier = Modifier.height(80.dp)      // Altura de la barra
            ) {
                // Crear una fila con los botones de navegación
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Crear cada botón de la barra de navegación
                    BottomNavItem.getItems().forEach { item ->
                        val isSelected = currentRoute?.destination?.route == item.getRoute("compact")
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .clickable { navController.navigate(item.getRoute("compact")) }
                                .padding(12.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = item.icon),
                                contentDescription = item.title,
                                tint = if (isSelected) GotGold else Color.Gray.copy(alpha = 0.6f),
                                modifier = Modifier.size(28.dp)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = item.title,
                                color = if (isSelected) GotGold else Color.Gray.copy(alpha = 0.6f),
                                fontSize = 12.sp,
                                fontFamily = FontFamily.Serif,
                                fontWeight = FontWeight.Normal
                            )
                        }
                    }
                }
            }
        }
    ) { paddingValues ->
        // Definir todas las pantallas de la app
        NavHost(
            navController = navController,
            startDestination = Routes.CharactersCompact.route,    // Pantalla inicial
            modifier = Modifier.padding(paddingValues)
        ) {
            // Definir cada pantalla de la app
            composable(Routes.CharactersCompact.route) {
                CompactCharacterListView(
                    modifier = Modifier,
                    navController = navController
                )
            }
            composable(Routes.HousesScreenCompact.route) {
                CompactHousesView(
                    navController = navController,
                    housesViewModel = housesViewModel
                )
            }
            composable(Routes.DeathViewCompact.route) {
                CompactDeathView(navController = navController)
            }
            composable(
                route = Routes.DetailScreen.route,
                arguments = listOf(navArgument("characterId") { type = NavType.StringType })
            ) { entry ->
                entry.arguments?.getString("characterId")?.let { id ->
                    CompactDetailScreen(
                        navController = navController,
                        characterId = id,
                        modifier = Modifier
                    )
                }
            }
            composable(
                route = Routes.HouseDetailScreenCompact.route,
                arguments = listOf(navArgument("houseId") { type = NavType.StringType })
            ) { entry ->
                entry.arguments?.getString("houseId")?.let { id ->
                    CompactHouseDetailScreen(
                        navController = navController,
                        houseId = id,
                        modifier = Modifier
                    )
                }
            }
        }
    }
} 