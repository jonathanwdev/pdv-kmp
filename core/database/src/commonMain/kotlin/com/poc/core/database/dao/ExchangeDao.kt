package com.poc.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.poc.core.database.entities.ExchangeEntity
import com.poc.core.database.entities.ExchangeItemEntity
import com.poc.core.database.entities.ExchangeWithCompleteDetails
import com.poc.core.database.entities.ExchangeWithItems
import kotlinx.coroutines.flow.Flow

@Dao
interface ExchangeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExchange(exchange: ExchangeEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExchangeItems(items: List<ExchangeItemEntity>)

    @Query("UPDATE sale_items SET returnedQuantity = returnedQuantity + :quantity WHERE itemId = :itemId")
    suspend fun increaseReturnedQuantity(itemId: Long, quantity: Int)

    @Transaction
    suspend fun performExchangeTransaction(exchange: ExchangeEntity, items: List<ExchangeItemEntity>) {
        val newExchangeId = insertExchange(exchange)
        val itemsWithId = items.map { it.copy(exchangeId = newExchangeId) }

        insertExchangeItems(itemsWithId)
        itemsWithId.forEach { item ->
            increaseReturnedQuantity(itemId = item.saleItemId, quantity = item.quantity)
        }
    }

    @Transaction
    @Query("SELECT * FROM exchanges WHERE exchangeId = :exchangeId")
    suspend fun getExchangeWithItemsById(exchangeId: Long): ExchangeWithItems?

    @Transaction
    @Query("SELECT * FROM exchanges ORDER BY timestamp DESC")
    fun getAllExchangesWithItems(): Flow<List<ExchangeWithItems>>

    @Transaction
    @Query("SELECT * FROM exchanges WHERE originalSaleId = :saleId ORDER BY timestamp DESC")
    fun getExchangesForSale(saleId: Long): Flow<List<ExchangeWithItems>>

    @Transaction
    @Query("SELECT * FROM exchanges WHERE exchangeId = :id")
    suspend fun getExchangeDeepDetails(id: Long): ExchangeWithCompleteDetails?
}