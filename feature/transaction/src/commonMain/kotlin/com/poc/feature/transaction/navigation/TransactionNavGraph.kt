package com.poc.feature.transaction.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.poc.feature.transaction.screens.transactionDetails.TransactionDetailsRoot
import com.poc.feature.transaction.screens.transactions.TransactionsRoot
import kotlinx.serialization.Serializable

sealed interface TransactionNavRoutes {
    @Serializable
    data object Graph: TransactionNavRoutes

    @Serializable
    data object TransactionsRoute: TransactionNavRoutes

    @Serializable
    data object TransactionDetailRoute: TransactionNavRoutes

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
                onNavigateBackToHome = { onNavigateBackToHome()},
                onNavigateToTransactionDetails = {
                    navController.navigate(TransactionNavRoutes.TransactionDetailRoute)
                }
            )

        }
        composable<TransactionNavRoutes.TransactionDetailRoute> {
            TransactionDetailsRoot(
                onNavigateBackToTransactions = {
                    navController.popBackStack()
                }
            )
        }
    }
}