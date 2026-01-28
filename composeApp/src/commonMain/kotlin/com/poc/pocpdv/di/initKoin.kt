package com.poc.pocpdv.di

import com.poc.core.data.di.coreDataModule
import com.poc.core.network.di.coreNetworkModule
import com.poc.feature.catalog.di.featureCatalogModule
import com.poc.feature.exchange.di.featureExchangeModule
import com.poc.feature.sale.di.featureSaleModule
import com.poc.feature.sync.di.featureSyncModule
import com.poc.feature.transaction.di.featureTransactionsModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            coreNetworkModule,
            coreDataModule,
            featureSyncModule,
            featureSaleModule,
            featureTransactionsModule,
            featureCatalogModule,
            featureExchangeModule
        )
    }
}