package com.poc.core.domain.repository

import com.poc.core.domain.models.Sale
import kotlinx.coroutines.flow.Flow

interface SaleRepository {
    fun findAllSalesWithItemsFlow(): Flow<List<Sale>>
    suspend fun findSaleById(saleId: Long): Result<Sale>
    suspend fun findSaleWithItemsById(saleId: Long): Result<Sale>
    suspend fun saveSale(sale: Sale)

}