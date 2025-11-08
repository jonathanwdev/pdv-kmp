package com.poc.core.domain.repository

import com.poc.core.domain.models.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProductRemote(): Flow<Product?>
    fun getProductLocal(): Flow<Product?>
}