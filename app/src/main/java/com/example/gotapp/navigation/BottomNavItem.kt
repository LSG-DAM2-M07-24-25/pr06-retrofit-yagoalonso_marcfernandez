package com.example.gotapp.navigation

import androidx.compose.runtime.Composable
import com.example.gotapp.R

sealed class BottomNavItem(
    private val baseRoute: String,
    val title: String,
    val icon: Int
) {
    fun getRoute(deviceType: String): String {
        return when (deviceType) {
            "compact" -> "${baseRoute}_compact"
            "medium" -> "${baseRoute}_medium"
            "expanded" -> "${baseRoute}_expanded"
            else -> "${baseRoute}_compact"
        }
    }

    object Characters : BottomNavItem(
        baseRoute = "characters",
        title = "Characters",
        icon = R.drawable.person
    )
    
    object Deaths : BottomNavItem(
        baseRoute = "death_view",
        title = "Deaths",
        icon = R.drawable.skull
    )
    
    object Houses : BottomNavItem(
        baseRoute = "houses_screen",
        title = "Houses",
        icon = R.drawable.shield
    )

    companion object {
        fun getItems() = listOf(
            Characters,    // Izquierda
            Deaths,       // Centro
            Houses        // Derecha
        )
    }
}