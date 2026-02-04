package com.poc.feature.sale.navigation

import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.poc.feature.sale.models.SALE_SCOPE_INSTANCE_ID
import com.poc.feature.sale.models.SaleFlowScopeId
import com.poc.feature.sale.screens.bag.BagRoot
import com.poc.feature.sale.screens.payment.PaymentRoot
import com.poc.feature.sale.screens.saleDetails.SaleDetailsRoot
import com.poc.feature.sale.screens.summary.SummaryRoot
import kotlinx.serialization.Serializable
import org.koin.compose.currentKoinScope
import org.koin.core.Koin

sealed interface SaleNavRoutes {
    @Serializable
    data object Graph : SaleNavRoutes

    @Serializable
    data object SaleBagRoute : SaleNavRoutes

    @Serializable
    data object SaleDetailsRoute : SaleNavRoutes

    @Serializable
    data object SalePaymentRoute : SaleNavRoutes

    @Serializable
    data object SaleSummaryRoute : SaleNavRoutes
}

fun Koin.clearSaleScope() {
    getScopeOrNull(SALE_SCOPE_INSTANCE_ID)?.close()
}

fun NavGraphBuilder.saleNavGraph(
    onNavigateBackToHome: () -> Unit,
    navController: NavHostController
) {
    navigation<SaleNavRoutes.Graph>(
        startDestination = SaleNavRoutes.SaleBagRoute
    ) {
        composable<SaleNavRoutes.SaleBagRoute> {
            val koinApplication = currentKoinScope().getKoin()
            val koinScope = remember {
                koinApplication.getOrCreateScope(SALE_SCOPE_INSTANCE_ID, SaleFlowScopeId)
            }

            BagRoot(
                koinScope = koinScope,
                onNavigateToSaleDetails = {
                    navController.navigate(SaleNavRoutes.SaleDetailsRoute)
                },
                onNavigateBackToHome = {
                    koinApplication.clearSaleScope()
                    onNavigateBackToHome()

                },
            )
        }

        composable<SaleNavRoutes.SaleDetailsRoute> {
            val koin = currentKoinScope().getKoin()
            val koinScope = koin.getOrCreateScope(SALE_SCOPE_INSTANCE_ID, SaleFlowScopeId)

            SaleDetailsRoot(
                koinScope = koinScope,
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateBackToHome = {
                    koin.clearSaleScope()
                    onNavigateBackToHome()
                },
                onNavigateToPayment = {
                    navController.navigate(SaleNavRoutes.SalePaymentRoute)
                },
            )
        }

        composable<SaleNavRoutes.SalePaymentRoute> {
            val koin = currentKoinScope().getKoin()
            val koinScope = koin.getOrCreateScope(SALE_SCOPE_INSTANCE_ID, SaleFlowScopeId)

            PaymentRoot(
                koinScope = koinScope,
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateBackToHome = {
                    koin.clearSaleScope()
                    onNavigateBackToHome()
                },
                onNavigateToSummary = {
                    navController.navigate(SaleNavRoutes.SaleSummaryRoute)
                },
            )
        }

        composable<SaleNavRoutes.SaleSummaryRoute> {
            val koin = currentKoinScope().getKoin()
            val koinScope = koin.getOrCreateScope(SALE_SCOPE_INSTANCE_ID, SaleFlowScopeId)


            SummaryRoot(
                koinScope = koinScope,
                onNavigateBackToHome = {
                    onNavigateBackToHome()
                    koin.clearSaleScope()
                },
            )
        }
    }
}