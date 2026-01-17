package com.poc.feature.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.poc.feature.home.screens.home.HomeRoot
import kotlinx.serialization.Serializable

sealed interface HomeRoutes {
    @Serializable
    data object Graph: HomeRoutes

    @Serializable
    data object HomeRoute: HomeRoutes

}


fun NavGraphBuilder.homeNavGraph(
    onNavigateToSale: () -> Unit,
    onNavigateToCatalog: () -> Unit,
    onNavigateToExchange: () -> Unit,
    onNavigateToTransactions: () -> Unit,
) {
    navigation<HomeRoutes.Graph>(
        startDestination = HomeRoutes.HomeRoute
    ) {
        composable<HomeRoutes.HomeRoute> {
            HomeRoot(
                onNavigateToSale = onNavigateToSale,
                onNavigateToCatalog = onNavigateToCatalog,
                onNavigateToExchange = onNavigateToExchange,
                onNavigateToTransactions = onNavigateToTransactions
            )

        }
    }
}



