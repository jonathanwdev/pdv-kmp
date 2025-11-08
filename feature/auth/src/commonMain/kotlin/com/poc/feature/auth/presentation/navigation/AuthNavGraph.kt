package com.poc.feature.auth.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.poc.feature.auth.presentation.screens.home.HomeRoot
import com.poc.feature.auth.presentation.screens.splash.SplashRoot

fun NavGraphBuilder.authGraph(
    navController: NavController,
    onNavigateToSale: () -> Unit,
    onNavigateToProducts: () -> Unit,
    onNavigateToLastSales: () -> Unit,
) {
    navigation<AuthRoutes.Graph>(
        startDestination = AuthRoutes.Splash,
    ) {
        composable<AuthRoutes.Splash> {
            SplashRoot(
                onNavigateToHome = {
                    navController.navigate(
                        AuthRoutes.Home,
                    ) {
                        popUpTo(AuthRoutes.Splash) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable<AuthRoutes.Home> {
            HomeRoot(
                onNavigateToSale = onNavigateToSale,
                onNavigateToProducts = onNavigateToProducts,
                onNavigateToLastSales = onNavigateToLastSales
            )
        }

    }
}