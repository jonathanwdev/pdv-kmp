package com.poc.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.poc.core.database.entities.SaleEntity
import com.poc.core.database.entities.SaleItemEntity
import com.poc.core.database.entities.SaleWithProducts
import kotlinx.coroutines.flow.Flow


@Dao
interface SaleDao {

    @Query("SELECT * FROM sales")
    fun findAllSales(): Flow<List<SaleEntity>>

    @Query("SELECT * FROM sales WHERE saleId = :saleId")
    fun findSaleBySaleId(saleId: Long): Flow<SaleEntity>

    @Transaction
    @Query("SELECT * FROM sales")
    fun findAllSalesWithProducts(): Flow<List<SaleWithProducts>>

    @Insert
    suspend fun insertSale(sale: SaleEntity): Long

    @Insert
    suspend fun insertSaleItems(saleItem: SaleItemEntity): Long

    @Transaction
    suspend fun insertSaleWithProducts(sale: SaleEntity, saleItems: List<SaleItemEntity>) {
        insertSale(sale)
        saleItems.forEach {
            insertSaleItems(it)
        }
    }
}