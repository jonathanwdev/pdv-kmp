package com.poc.pocpdv.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.poc.feature.auth.presentation.navigation.authGraph
import com.poc.feature.auth.presentation.navigation.AuthRoutes

@Composable
fun RootNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AuthRoutes.Graph
    ) {
        authGraph(
            navController = navController,
            onNavigateToLastSales = {},
            onNavigateToProducts = {},
            onNavigateToSale = {}

        )
    }
}