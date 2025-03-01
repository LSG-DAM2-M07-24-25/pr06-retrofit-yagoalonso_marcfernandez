package com.example.gotapp.navigation

sealed class Routes(val route: String) {
    object Characters : Routes("characters_screen")
    object Deaths : Routes("deaths_screen")
    object Houses : Routes("houses_screen")
    object DetailScreen : Routes("detail_screen/{id}") {
        fun createRoute(id: String) = "detail_screen/$id"
    }
}