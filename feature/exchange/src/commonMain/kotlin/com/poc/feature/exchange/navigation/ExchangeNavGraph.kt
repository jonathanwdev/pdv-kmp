package com.poc.feature.exchange.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.poc.feature.exchange.screens.findSale.FindSaleRoot
import com.poc.feature.exchange.screens.selectItems.SelectItemsRoot
import com.poc.feature.exchange.screens.summary.SummaryRoot
import kotlinx.serialization.Serializable

sealed interface ExchangeNavRoutes {
    @Serializable
    data object Graph: ExchangeNavRoutes

    @Serializable
    data object FindSaleRoute: ExchangeNavRoutes

    @Serializable
    data object SelectItemsRoute: ExchangeNavRoutes

    @Serializable
    data object SummaryRoute: ExchangeNavRoutes

}


fun NavGraphBuilder.exchangeNavGraph(
    navController: NavController,
    onNavigateBackToHome: () -> Unit
) {
    navigation<ExchangeNavRoutes.Graph>(
        startDestination = ExchangeNavRoutes.FindSaleRoute
    ) {
        composable<ExchangeNavRoutes.FindSaleRoute> {
            FindSaleRoot(
                onNavigateBackToHome = {
                    onNavigateBackToHome()
                },
                onNavigateToSelectItems = {
                    navController.navigate(ExchangeNavRoutes.SelectItemsRoute)
                }
            )
        }
        composable<ExchangeNavRoutes.SelectItemsRoute> {
            SelectItemsRoot(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToSummary = {
                    navController.navigate(ExchangeNavRoutes.SummaryRoute)
                }
            )
        }
        composable<ExchangeNavRoutes.SummaryRoute> {
            SummaryRoot(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToHome = {
                    onNavigateBackToHome()
                }
            )
        }

    }
}