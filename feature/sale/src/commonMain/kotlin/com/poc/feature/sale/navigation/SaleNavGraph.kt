package com.poc.feature.sale.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.poc.feature.sale.screens.bag.BagRoot
import com.poc.feature.sale.screens.payment.PaymentRoot
import com.poc.feature.sale.screens.saleDetails.SaleDetailsRoot
import com.poc.feature.sale.screens.summary.SummaryRoot
import kotlinx.serialization.Serializable

sealed interface SaleNavGraph {
    @Serializable
    data object Graph: SaleNavGraph

    @Serializable
    data object SaleBagRoute: SaleNavGraph

    @Serializable
    data object SaleDetailsRoute: SaleNavGraph

    @Serializable
    data object SalePaymentRoute: SaleNavGraph

    @Serializable
    data object SaleSummaryRoute: SaleNavGraph
}


fun NavGraphBuilder.saleNavGraph(
    onNavigateBackToHome: () -> Unit,
    navController: NavHostController
) {
    navigation<SaleNavGraph.Graph>(
        startDestination = SaleNavGraph.SaleBagRoute
    ) {
        composable<SaleNavGraph.SaleBagRoute> {
            BagRoot(
                onNavigateToSaleDetails = {
                    navController.navigate(SaleNavGraph.SaleDetailsRoute)
                }
            )
        }

        composable<SaleNavGraph.SaleDetailsRoute> {
            SaleDetailsRoot(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToPayment = {
                    navController.navigate(SaleNavGraph.SalePaymentRoute)
                }
            )
        }

        composable<SaleNavGraph.SalePaymentRoute> {
            PaymentRoot(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToSummary = {
                    navController.navigate(SaleNavGraph.SaleSummaryRoute)
                }
            )
        }

        composable<SaleNavGraph.SaleSummaryRoute> {
            SummaryRoot(
                onNavigateToStartSale = {
                    navController.navigate(SaleNavGraph.SaleBagRoute){
                        popUpTo(SaleNavGraph.SaleBagRoute){
                            inclusive = true
                        }
                    }
                },
                onNavigateBackToHome = {
                    onNavigateBackToHome()
                }
            )
        }

    }
}