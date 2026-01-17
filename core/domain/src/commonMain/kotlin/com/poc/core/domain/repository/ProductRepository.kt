package com.poc.core.domain.repository

import com.poc.core.domain.models.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProductRemote(sku: String): Flow<Product?>
    fun getProductLocal(sku: String): Flow<Product?>

}