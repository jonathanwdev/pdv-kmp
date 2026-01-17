package com.poc.core.network.di

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.dsl.module


actual val platformCoreNetworkModule = module {
    single<HttpClientEngine> {
        OkHttp.create()
    }
}