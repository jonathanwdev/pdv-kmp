package com.poc.core.data.repository

import com.poc.core.data.mappers.toEntity
import com.poc.core.database.PocPdvDatabase
import com.poc.core.domain.repository.SyncRepository
import com.poc.core.network.dataSource.ProductDataSource

class SyncRepositoryImpl(
    private val productDataSource: ProductDataSource,
    private val pocPdvDatabase: PocPdvDatabase
): SyncRepository {
    override suspend fun syncProducts() {
        val hasProducts = pocPdvDatabase.productDao.getProducts()
        if(hasProducts.isNotEmpty()) return
        val products = productDataSource.getProducts()
        pocPdvDatabase.productDao.upsertAll(products.map { it.toEntity() })
    }
}