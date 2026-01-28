package com.poc.feature.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.poc.feature.home.screens.home.HomeRoot
import kotlinx.serialization.Serializable

sealed interface HomeNavRoutes {
    @Serializable
    data object Graph: HomeNavRoutes

    @Serializable
    data object HomeRoute: HomeNavRoutes

}


fun NavGraphBuilder.homeNavGraph(
    onNavigateToSale: () -> Unit,
    onNavigateToCatalog: () -> Unit,
    onNavigateToExchange: () -> Unit,
    onNavigateToTransactions: () -> Unit,
) {
    navigation<HomeNavRoutes.Graph>(
        startDestination = HomeNavRoutes.HomeRoute
    ) {
        composable<HomeNavRoutes.HomeRoute> {
            HomeRoot(
                onNavigateToSale = onNavigateToSale,
                onNavigateToCatalog = onNavigateToCatalog,
                onNavigateToExchange = onNavigateToExchange,
                onNavigateToTransactions = onNavigateToTransactions
            )

        }
    }
}



