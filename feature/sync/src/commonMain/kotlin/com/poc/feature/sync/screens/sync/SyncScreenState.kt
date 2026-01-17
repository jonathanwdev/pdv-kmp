package com.poc.feature.sync.screens.sync

import org.jetbrains.compose.resources.StringResource
import pocpdv.feature.sync.generated.resources.Res
import pocpdv.feature.sync.generated.resources.initializing_sync


data class SyncScreenState(
    val progress: Float = 0.0f,
    val message: StringResource = Res.string.initializing_sync,
    val isDone: Boolean = false
)