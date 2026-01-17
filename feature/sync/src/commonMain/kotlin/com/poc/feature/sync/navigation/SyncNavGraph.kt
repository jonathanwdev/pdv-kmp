package com.poc.feature.sync.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.poc.feature.sync.screens.sync.SyncRoot
import kotlinx.serialization.Serializable

sealed interface SyncRoutes {
    @Serializable
    data object Graph: SyncRoutes

    @Serializable
    data object SyncRoute: SyncRoutes

}


fun NavGraphBuilder.syncNavGraph(
    onNavigateToHome: () -> Unit
) {
    navigation<SyncRoutes.Graph>(
        startDestination = SyncRoutes.SyncRoute
    ) {
        composable<SyncRoutes.SyncRoute> {
            SyncRoot(
                onSyncDone = onNavigateToHome
            )

        }
    }
}