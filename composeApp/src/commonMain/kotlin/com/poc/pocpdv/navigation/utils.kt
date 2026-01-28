package com.poc.pocpdv.navigation

import androidx.navigation.NavHostController
import com.poc.feature.home.navigation.HomeNavRoutes

fun NavHostController.navigateToHomeClearStack() {
    navigate(
        route = HomeNavRoutes.HomeRoute,
    ) {
        popUpTo(HomeNavRoutes.Graph) {
            inclusive = true
        }

    }
}
