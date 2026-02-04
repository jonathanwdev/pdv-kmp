package com.poc.feature.exchange.navigation

import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.poc.feature.exchange.models.EXCHANGE_SCOPE_INSTANCE_ID
import com.poc.feature.exchange.models.ExchangeFlowScopeId
import com.poc.feature.exchange.screens.findSale.FindSaleRoot
import com.poc.feature.exchange.screens.selectItems.SelectItemsRoot
import com.poc.feature.exchange.screens.summary.SummaryRoot
import kotlinx.serialization.Serializable
import org.koin.compose.currentKoinScope
import org.koin.core.Koin

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

fun Koin.clearExchangeScope() {
    getScopeOrNull(EXCHANGE_SCOPE_INSTANCE_ID)?.close()
}

fun NavGraphBuilder.exchangeNavGraph(
    navController: NavController,
    onNavigateBackToHome: () -> Unit
) {
    navigation<ExchangeNavRoutes.Graph>(
        startDestination = ExchangeNavRoutes.FindSaleRoute
    ) {
        composable<ExchangeNavRoutes.FindSaleRoute> {
            val koinApplication = currentKoinScope().getKoin()
            val koinScope = remember {
                koinApplication.getOrCreateScope(EXCHANGE_SCOPE_INSTANCE_ID, ExchangeFlowScopeId)
            }

            FindSaleRoot(
                koinScope = koinScope,
                onNavigateBackToHome = {
                    onNavigateBackToHome()
                    koinApplication.clearExchangeScope()
                },
                onNavigateToSelectItems = {
                    navController.navigate(ExchangeNavRoutes.SelectItemsRoute)
                }
            )
        }
        composable<ExchangeNavRoutes.SelectItemsRoute> {
            val koin = currentKoinScope().getKoin()
            val koinScope = koin.getOrCreateScope(EXCHANGE_SCOPE_INSTANCE_ID, ExchangeFlowScopeId)

            SelectItemsRoot(
                koinScope = koinScope,
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToSummary = {
                    navController.navigate(ExchangeNavRoutes.SummaryRoute)
                }
            )
        }
        composable<ExchangeNavRoutes.SummaryRoute> {
            val koin = currentKoinScope().getKoin()
            val koinScope = koin.getOrCreateScope(EXCHANGE_SCOPE_INSTANCE_ID, ExchangeFlowScopeId)

            SummaryRoot(
                koinScope = koinScope,
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToHome = {
                    onNavigateBackToHome()
                    koin.clearExchangeScope()
                }
            )
        }

    }
}