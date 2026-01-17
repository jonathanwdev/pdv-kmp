package com.poc.pocpdv.di

import com.poc.core.data.di.coreDataModule
import com.poc.core.network.di.coreNetworkModule
import com.poc.feature.sale.di.featureSaleModule
import com.poc.feature.sync.di.featureSyncModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            coreNetworkModule,
            coreDataModule,
            featureSyncModule,
            featureSaleModule
        )
    }
}