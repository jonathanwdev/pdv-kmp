package com.poc.core.domain.repository

import com.poc.core.domain.models.Sale
import kotlinx.coroutines.flow.Flow

interface SaleRepository {
    fun findAllSalesWithItems(): Flow<List<Sale>>
    suspend fun saveSale(sale: Sale)

}