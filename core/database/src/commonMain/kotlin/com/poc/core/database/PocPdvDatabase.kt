package com.poc.core.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import com.poc.core.database.dao.ProductDao
import com.poc.core.database.entities.ProductEntity

@Database(
    entities = [
        ProductEntity::class
    ],
    version = 1
)
@ConstructedBy(DatabaseConstructor::class)
abstract class PocPdvDatabase: RoomDatabase() {
    abstract val productDao: ProductDao

    companion object {
        const val DB_NAME = "poc_pdv.db"
    }
}

