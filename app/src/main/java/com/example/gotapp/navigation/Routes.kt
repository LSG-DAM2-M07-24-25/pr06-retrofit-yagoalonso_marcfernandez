package com.example.gotapp.navigation

sealed class Routes(val route: String) {
    // Vistas Compact
    object CharactersCompact : Routes("characters_compact")
    object HousesScreenCompact : Routes("houses_screen_compact")
    object DetailScreenCompact : Routes("detail_screen_compact/{characterId}") {
        fun createRoute(characterId: String) = "detail_screen_compact/$characterId"
    }
    object HouseDetailScreenCompact : Routes("house_detail_screen_compact/{houseId}") {
        fun createRoute(houseId: String) = "house_detail_screen_compact/$houseId"
    }
    object DeathViewCompact : Routes("death_view_compact")

    // Vistas Medium
    object CharactersMedium : Routes("characters_medium")
    object HousesScreenMedium : Routes("houses_screen_medium")
    object DetailScreenMedium : Routes("detail_screen_medium/{characterId}") {
        fun createRoute(characterId: String) = "detail_screen_medium/$characterId"
    }
    object HouseDetailScreenMedium : Routes("house_detail_screen_medium/{houseId}") {
        fun createRoute(houseId: String) = "house_detail_screen_medium/$houseId"
    }
    object DeathViewMedium : Routes("death_view_medium")

    // Vistas Expanded
    object CharactersExpanded : Routes("characters_expanded")
    object HousesScreenExpanded : Routes("houses_screen_expanded")
    object DetailScreenExpanded : Routes("detail_screen_expanded/{characterId}") {
        fun createRoute(characterId: String) = "detail_screen_expanded/$characterId"
    }
    object HouseDetailScreenExpanded : Routes("house_detail_screen_expanded/{houseId}") {
        fun createRoute(houseId: String) = "house_detail_screen_expanded/$houseId"
    }
    object DeathViewExpanded : Routes("death_view_expanded")

    // Rutas Responsivas (detectarán automáticamente el tamaño)
    object Characters : Routes("characters")
    object HousesScreen : Routes("houses_screen")
    object DetailScreen : Routes("detail_screen/{characterId}") {
        fun createRoute(characterId: String) = "detail_screen/$characterId"
    }
    object HouseDetailScreen : Routes("house_detail_screen/{houseId}") {
        fun createRoute(houseId: String) = "house_detail_screen/$houseId"
    }
    object DeathView : Routes("death_view")

    companion object {
        fun getRouteForSize(baseRoute: String, deviceType: String): String {
            return when (deviceType) {
                "compact" -> "${baseRoute}_compact"
                "medium" -> "${baseRoute}_medium"
                "expanded" -> "${baseRoute}_expanded"
                else -> "${baseRoute}_compact"
            }
        }
    }
}