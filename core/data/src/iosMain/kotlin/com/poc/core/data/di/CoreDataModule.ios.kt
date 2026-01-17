package com.poc.core.data.di

import com.poc.core.database.DatabaseFactory
import org.koin.dsl.module

actual val platformCoreDataModule = module {
    single { DatabaseFactory() }
}