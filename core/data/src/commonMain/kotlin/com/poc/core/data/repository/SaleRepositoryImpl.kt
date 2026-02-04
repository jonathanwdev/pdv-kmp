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
) : SaleRepository {
    override fun findAllSalesWithItemsFlow(): Flow<List<Sale>> {
        return database.saleDao.findAllSalesWithProductsFlow().map { allSales ->
            supervisorScope {
                allSales.map { it.toDomain() }
            }
        }
    }

    override suspend fun findSaleById(saleId: Long): Result<Sale> {
        return runCatching {
            val sale = database.saleDao.findSaleBySaleId(saleId)
            sale?.toDomain(emptyList()) ?: throw Exception("Sale not found")
        }
    }

    override suspend fun findSaleWithItemsById(saleId: Long): Result<Sale> {
        return runCatching {
            val sale = database.saleDao.findSaleWithProductsById(saleId)
            sale?.toDomain() ?: throw Exception("Sale not found")
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