package com.poc.core.network.di

import com.poc.core.network.config.HttpClientFactory
import com.poc.core.network.dataSource.ProductDataSource
import com.poc.core.network.dataSource.ProductDataSourceImpl
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


expect val platformCoreNetworkModule: Module


val coreNetworkModule = module {
    includes(platformCoreNetworkModule)
    single {
        HttpClientFactory().create(get())
    }
    singleOf(::ProductDataSourceImpl) bind ProductDataSource::class

}