package com.example.gotapp.navigation

import com.example.gotapp.R

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: Int
) {
    object Characters : BottomNavItem(
        route = Routes.Characters.route,
        title = "Characters",
        icon = R.drawable.person
    )
    
    object Deaths : BottomNavItem(
        route = Routes.Deaths.route,
        title = "Deaths",
        icon = R.drawable.skull
    )
    
    object Houses : BottomNavItem(
        route = Routes.Houses.route,
        title = "Houses",
        icon = R.drawable.shield
    )
}