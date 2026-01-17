package com.poc.pocpdv.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.poc.feature.home.navigation.HomeRoutes
import com.poc.feature.home.navigation.homeNavGraph
import com.poc.feature.sale.navigation.SaleNavGraph
import com.poc.feature.sale.navigation.saleNavGraph
import com.poc.feature.sync.navigation.SyncRoutes
import com.poc.feature.sync.navigation.syncNavGraph

@Composable
fun RootNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = SyncRoutes.Graph
    ) {
        syncNavGraph(
            onNavigateToHome = {
                navController.navigate(
                    route = HomeRoutes.HomeRoute,
                ) {
                    popUpTo(SyncRoutes.Graph) {
                        inclusive = true
                    }
                }
            }
        )
        homeNavGraph(
            onNavigateToSale = {
                navController.navigate(
                    route = SaleNavGraph.SaleBagRoute,
                )
            },
            onNavigateToCatalog = {

            },
            onNavigateToExchange = {

            },
            onNavigateToTransactions = {

            }
        )
        saleNavGraph(
            navController = navController,
            onNavigateBackToHome = {
                navController.navigate(
                    route = HomeRoutes.HomeRoute,
                ) {
                    popUpTo(HomeRoutes.Graph) {
                        inclusive = true
                    }
                }
            }
        )
    }

}
