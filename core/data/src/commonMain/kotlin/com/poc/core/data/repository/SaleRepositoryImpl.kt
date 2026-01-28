package com.poc.core.data.repository

import androidx.room.Transaction
import com.poc.core.data.mappers.toDomain
import com.poc.core.data.mappers.toEntity
import com.poc.core.data.mappers.toTransactionEntity
import com.poc.core.database.PocPdvDatabase
import com.poc.core.domain.models.Sale
import com.poc.core.domain.repository.SaleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.supervisorScope

class SaleRepositoryImpl(
    private val database: PocPdvDatabase,
): SaleRepository {
    override fun findAllSalesWithItems(): Flow<List<Sale>> {
        return database.saleDao.findAllSalesWithProducts().map { allSales ->
            supervisorScope {
                allSales.map { it.toDomain() }
            }
        }
    }

    @Transaction
    override suspend fun saveSale(sale: Sale) {
        val saleEntity = sale.toEntity()
        database.saleDao.insertSaleWithProducts(
            sale = sale.toEntity(),
            saleItems = sale.items.map { it.toEntity() }
        )
        database.transactionDao.insertTransaction(saleEntity.toTransactionEntity())
    }

}