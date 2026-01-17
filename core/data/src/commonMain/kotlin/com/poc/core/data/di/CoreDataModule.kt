package com.poc.core.data.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.poc.core.data.repository.SyncRepositoryImpl
import com.poc.core.database.DatabaseFactory
import com.poc.core.domain.repository.SyncRepository
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformCoreDataModule: Module

val coreDataModule = module {
    includes(platformCoreDataModule)

    single {
        get<DatabaseFactory>().create().setDriver(BundledSQLiteDriver()).build()
    }
    singleOf(::SyncRepositoryImpl) bind  SyncRepository::class
}