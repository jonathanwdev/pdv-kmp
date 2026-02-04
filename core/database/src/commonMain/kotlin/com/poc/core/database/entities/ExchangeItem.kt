package com.poc.core.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "exchange_items",
    foreignKeys = [
        ForeignKey(
            entity = ExchangeEntity::class,
            parentColumns = ["exchangeId"],
            childColumns = ["exchangeId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = SaleItemEntity::class,
            parentColumns = ["itemId"],
            childColumns = ["saleItemId"],
            onDelete = ForeignKey.RESTRICT
        )
    ],
    indices = [
        Index(value = ["exchangeId"]),
        Index(value = ["saleItemId"])
    ]
)
data class ExchangeItemEntity(
    @PrimaryKey(autoGenerate = true)
    val exchangeItemId: Long = 0,
    val exchangeId: Long,       // Vincula ao evento de troca (cabeçalho)
    val saleItemId: Long,       // Vincula ao item original vendido
    val quantity: Int,          
    val creditValue: Double,
    val reason: String? = null
)