package com.poc.feature.transaction.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.poc.feature.transaction.screens.transactionDetails.TransactionDetailsRoot
import com.poc.feature.transaction.screens.transactions.TransactionsRoot
import kotlinx.serialization.Serializable

sealed interface TransactionNavRoutes {
    @Serializable
    data object Graph : TransactionNavRoutes

    @Serializable
    data object TransactionsRoute : TransactionNavRoutes

    @Serializable
    data class TransactionDetailRoute(val transactionId: Long) : TransactionNavRoutes

}


fun NavGraphBuilder.transactionNavGraph(
    navController: NavController,
    onNavigateBackToHome: () -> Unit
) {
    navigation<TransactionNavRoutes.Graph>(
        startDestination = TransactionNavRoutes.TransactionsRoute
    ) {
        composable<TransactionNavRoutes.TransactionsRoute> {
            TransactionsRoot(
                onNavigateBackToHome = {
                    onNavigateBackToHome()
                },
                onNavigateToTransactionDetails = { transactionId ->
                    navController.navigate(TransactionNavRoutes.TransactionDetailRoute(transactionId))
                }
            )

        }
        composable<TransactionNavRoutes.TransactionDetailRoute> { backStackEntry ->
            val params = backStackEntry.toRoute<TransactionNavRoutes.TransactionDetailRoute>()

            TransactionDetailsRoot(
                transactionId = params.transactionId,
                onNavigateBackToTransactions = {
                    navController.popBackStack()
                },
                onNavigateBackToHome = {
                    onNavigateBackToHome()
                }
            )
        }
    }
}