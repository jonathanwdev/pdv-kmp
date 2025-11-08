package com.poc.feature.auth.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface AuthRoutes {
    @Serializable
    object Graph : AuthRoutes

    @Serializable
    object Home : AuthRoutes
    
    @Serializable
    object Splash : AuthRoutes

}