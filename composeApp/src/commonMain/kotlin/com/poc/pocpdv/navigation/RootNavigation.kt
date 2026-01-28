package com.poc.pocpdv.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.poc.feature.exchange.navigation.ExchangeNavRoutes
import com.poc.feature.exchange.navigation.exchangeNavGraph
import com.poc.feature.home.navigation.HomeNavRoutes
import com.poc.feature.home.navigation.homeNavGraph
import com.poc.feature.sale.navigation.SaleNavRoutes
import com.poc.feature.sale.navigation.saleNavGraph
import com.poc.feature.sync.navigation.SyncNavRoutes
import com.poc.feature.sync.navigation.syncNavGraph
import com.poc.feature.transaction.navigation.TransactionNavRoutes
import com.poc.feature.transaction.navigation.transactionNavGraph


@Composable
fun RootNavigation() {
    val navController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = SyncNavRoutes.Graph
    ) {
        syncNavGraph(
            onNavigateToHome = {
                navController.navigate(
                    route = HomeNavRoutes.HomeRoute,
                ) {
                    popUpTo(SyncNavRoutes.Graph) {
                        inclusive = true
                    }
                }
            }
        )
        homeNavGraph(
            onNavigateToSale = {
                navController.navigate(
                    route = SaleNavRoutes.SaleBagRoute,
                )
            },
            onNavigateToCatalog = {

            },
            onNavigateToExchange = {
                navController.navigate(
                    route = ExchangeNavRoutes.FindSaleRoute,
                )
            },
            onNavigateToTransactions = {
                navController.navigate(
                    route = TransactionNavRoutes.TransactionsRoute,
                )
            }
        )
        saleNavGraph(
            navController = navController,
            onNavigateBackToHome = {
                navController.navigateToHomeClearStack()
            }
        )
        transactionNavGraph(
            navController = navController,
            onNavigateBackToHome = {
                navController.navigateToHomeClearStack()
            }
        )
        exchangeNavGraph(
            navController = navController,
            onNavigateBackToHome = {
                navController.navigateToHomeClearStack()
            }
        )
    }

}
