package com.poc.core.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "exchanges",
    foreignKeys = [
        ForeignKey(
            entity = SaleEntity::class,
            parentColumns = ["saleId"],
            childColumns = ["originalSaleId"],
            onDelete = ForeignKey.SET_NULL
        )
    ]
)
data class ExchangeEntity(
    @PrimaryKey()
    val exchangeId: Long,
    val originalSaleId: Long,
    val timestamp: Long,
    val reason: String?,
    val creditValue: Double
)
