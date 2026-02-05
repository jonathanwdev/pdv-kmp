package com.poc.core.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import com.poc.core.database.dao.ExchangeDao
import com.poc.core.database.dao.ProductDao
import com.poc.core.database.dao.SaleDao
import com.poc.core.database.dao.SaleItemsDao
import com.poc.core.database.dao.TransactionDao
import com.poc.core.database.entities.ExchangeEntity
import com.poc.core.database.entities.ExchangeItemEntity
import com.poc.core.database.entities.ProductEntity
import com.poc.core.database.entities.SaleEntity
import com.poc.core.database.entities.SaleItemEntity
import com.poc.core.database.entities.TransactionEntity

@Database(
    entities = [
        ProductEntity::class,
        SaleEntity::class,
        ExchangeEntity::class,
        SaleItemEntity::class,
        TransactionEntity::class,
        ExchangeItemEntity::class
    ],
    version = 1
)
@ConstructedBy(DatabaseConstructor::class)
abstract class PocPdvDatabase : RoomDatabase() {
    abstract val productDao: ProductDao
    abstract val saleDao: SaleDao
    abstract val saleItemsDao: SaleItemsDao
    abstract val transactionDao: TransactionDao
    abstract val exchangeDao: ExchangeDao

    companion object {
        const val DB_NAME = "poc_pdv.db"
    }
}
