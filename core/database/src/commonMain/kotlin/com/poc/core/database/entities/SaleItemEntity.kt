package com.poc.core.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "sale_items",
    foreignKeys = [
        ForeignKey(
            entity = SaleEntity::class,
            parentColumns = ["saleId"],
            childColumns = ["saleId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class SaleItemEntity(
    @PrimaryKey(autoGenerate = true)
    val itemId: Long = 0,
    val saleId: Long,
    val name: String,
    val productSku: Int,
    val imageUrl: String,
    val quantity: Int,
    val price: Double,
    val tax: Double,
    val totalPrice: Double,
    val isExchanged: Boolean = false
)


