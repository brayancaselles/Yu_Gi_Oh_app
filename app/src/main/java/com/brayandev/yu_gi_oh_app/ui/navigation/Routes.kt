package com.brayandev.yu_gi_oh_app.ui.navigation

const val ARCHETYPE_NAME = "archetype_name"

sealed class Routes(val route: String) {
    object Archetypes : Routes("archetypes")
    object DetailsArchetype : Routes("detailsArchetype/{$ARCHETYPE_NAME}") {
        fun createRoute(archetypeName: String): String {
            return "detailsArchetype/$archetypeName"
        }
    }
}
