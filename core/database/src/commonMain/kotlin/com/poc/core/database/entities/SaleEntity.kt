package com.poc.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sales")
data class SaleEntity(
    @PrimaryKey()
    val saleId: Long,
    val timestamp: Long,
    val totalValue: Double,
    val totalTaxValue: Double,
    val taxPercentage: Double,
    val paymentMethod: String,
)
