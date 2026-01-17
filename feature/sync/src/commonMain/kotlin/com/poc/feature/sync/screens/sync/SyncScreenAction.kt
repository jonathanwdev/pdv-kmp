package com.poc.feature.sync.screens.sync

sealed interface SyncScreenAction {
    data object OnSyncDone: SyncScreenAction
}