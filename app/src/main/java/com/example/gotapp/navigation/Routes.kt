package com.example.gotapp.navigation

sealed class Routes(val route: String) {
    object Characters : Routes("characters")
    object Deaths : Routes("deaths")
    object Houses : Routes("houses")
    object DetailScreen : Routes("detailScreen/{characterId}") {
        fun createRoute(characterId: String) = "detailScreen/$characterId"
    }
}