package com.poc.core.data.repository

import com.poc.core.data.mappers.toDomain
import com.poc.core.database.PocPdvDatabase
import com.poc.core.domain.models.Product
import com.poc.core.domain.repository.ProductRepository

class ProductRepositoryImpl(
    private val database: PocPdvDatabase
) : ProductRepository {
    override suspend fun getProductBySkuLocal(sku: String): Result<Product> {
        return runCatching {
            val product = database.productDao.getProductsBySku(sku.toLong())
            product?.toDomain() ?: throw Exception("Product not found")
        }

    }
}