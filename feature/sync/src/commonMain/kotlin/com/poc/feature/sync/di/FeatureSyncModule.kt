package com.poc.feature.sync.di

import com.poc.feature.sync.screens.sync.SyncViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val featureSyncModule = module {
    viewModelOf(::SyncViewModel)
}