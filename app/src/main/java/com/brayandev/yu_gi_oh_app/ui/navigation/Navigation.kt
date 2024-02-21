package com.brayandev.yu_gi_oh_app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.brayandev.yu_gi_oh_app.ui.views.DetailCards.DetailCardsScreen
import com.brayandev.yu_gi_oh_app.ui.views.DetailCards.DetailViewModel
import com.brayandev.yu_gi_oh_app.ui.views.archetype.ArchetypeScreen
import com.brayandev.yu_gi_oh_app.ui.views.archetype.ArchetypeViewModel

@Composable
fun NavigationScreen() {
    val navigationController = rememberNavController()
    NavHost(navController = navigationController, startDestination = Routes.Archetypes.route) {
        composable(Routes.Archetypes.route) {
            val viewModel = hiltViewModel<ArchetypeViewModel>()
            ArchetypeScreen(viewModel, navigationController)
        }
        composable(
            Routes.DetailsArchetype.route,
            arguments = listOf(navArgument(ARCHETYPE_NAME) { type = NavType.StringType }),
        ) {
            val viewModel = hiltViewModel<DetailViewModel>()
            DetailCardsScreen(viewModel)
        }
    }
}
