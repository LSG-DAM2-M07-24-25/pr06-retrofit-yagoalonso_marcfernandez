package com.example.gotapp.navigation

sealed class Routes(val route: String) {
    object CharacterList : Routes("characterList")
    object DetailScreen : Routes("detailScreen/{characterId}") {
        fun createRoute(characterId: String) = "detailScreen/$characterId"
    }
}