package com.poc.core.network.di

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import org.koin.dsl.module

actual val platformCoreNetworkModule = module {
    single<HttpClientEngine> {
        Darwin.create()
    }
}