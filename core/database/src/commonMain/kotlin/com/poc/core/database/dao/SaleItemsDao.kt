package com.poc.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.poc.core.database.entities.SaleItemEntity


@Dao
interface SaleItemsDao {
    @Insert
    suspend fun insertSaleItem(saleItem: SaleItemEntity): Long


}