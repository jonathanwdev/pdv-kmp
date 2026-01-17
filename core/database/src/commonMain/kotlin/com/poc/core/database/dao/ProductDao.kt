package com.poc.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.poc.core.database.entities.ProductEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface ProductDao {
    @Upsert
    suspend fun upsertAll(products: List<ProductEntity>)

    @Upsert
    suspend fun upsert(product: ProductEntity)

    @Query("SELECT * FROM productentity WHERE sku = :sku")
    fun getProductsBySku(sku: Long): Flow<List<ProductEntity>>

}