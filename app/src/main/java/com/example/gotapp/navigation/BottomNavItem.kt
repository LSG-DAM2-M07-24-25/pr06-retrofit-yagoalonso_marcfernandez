// Este archivo define los elementos que aparecerán en la barra de navegación inferior
package com.example.gotapp.navigation

import androidx.compose.runtime.Composable
import com.example.gotapp.R

// Clase que define cómo será cada botón de la barra de navegación
sealed class BottomNavItem(
    private val baseRoute: String,    // Ruta base para la navegación
    val title: String,               // Texto que aparecerá debajo del icono
    val icon: Int                    // Icono que se mostrará
) {
    // Función que devuelve la ruta según el tipo de pantalla (compact, medium, expanded)
    fun getRoute(deviceType: String): String {
        return when (deviceType) {
            "compact" -> "${baseRoute}_compact"
            "medium" -> "${baseRoute}_medium"
            "expanded" -> "${baseRoute}_expanded"
            else -> "${baseRoute}_compact"
        }
    }

    // Definición del botón de Personajes
    object Characters : BottomNavItem(
        baseRoute = "characters",
        title = "Characters",
        icon = R.drawable.person
    )
    
    // Definición del botón de Muertes
    object Deaths : BottomNavItem(
        baseRoute = "death_view",
        title = "Deaths",
        icon = R.drawable.skull
    )
    
    // Definición del botón de Casas
    object Houses : BottomNavItem(
        baseRoute = "houses_screen",
        title = "Houses",
        icon = R.drawable.shield
    )

    // Lista que define el orden de los botones en la barra
    companion object {
        fun getItems() = listOf(
            Characters,    // Botón izquierdo
            Deaths,       // Botón central
            Houses        // Botón derecho
        )
    }
}