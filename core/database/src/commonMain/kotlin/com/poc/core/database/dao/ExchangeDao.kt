package com.poc.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.poc.core.database.entities.ExchangeEntity
import com.poc.core.database.entities.SaleItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExchangeDao {
    @Insert
    suspend fun insertExchange(exchange: ExchangeEntity): Long

    @Query("SELECT * FROM exchanges")
    fun findAllExchanges(): Flow<List<ExchangeEntity>>

    @Upsert
    suspend fun upsertToExchanged(products: SaleItemEntity): Long

    @Transaction
    suspend fun insertExchangeWithProducts(exchange: ExchangeEntity, saleItems: List<SaleItemEntity>) {
        insertExchange(exchange)
        saleItems.forEach { product ->
            upsertToExchanged(product.copy(isExchanged = true))
        }
    }


}