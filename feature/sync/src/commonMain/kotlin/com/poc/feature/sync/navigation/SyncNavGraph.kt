package com.poc.feature.sync.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.poc.feature.sync.screens.sync.SyncRoot
import kotlinx.serialization.Serializable

sealed interface SyncNavRoutes {
    @Serializable
    data object Graph: SyncNavRoutes

    @Serializable
    data object SyncRoute: SyncNavRoutes

}


fun NavGraphBuilder.syncNavGraph(
    onNavigateToHome: () -> Unit
) {
    navigation<SyncNavRoutes.Graph>(
        startDestination = SyncNavRoutes.SyncRoute
    ) {
        composable<SyncNavRoutes.SyncRoute> {
            SyncRoot(
                onSyncDone = onNavigateToHome
            )

        }
    }
}