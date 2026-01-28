package com.poc.core.domain.repository

import com.poc.core.domain.models.Product

interface ProductRepository {
    suspend fun getProductBySkuLocal(sku: String):  Result<Product>
}