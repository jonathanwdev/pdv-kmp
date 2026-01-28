package com.poc.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val transactionId: Long = 0,
    val timestamp: Long,
    val amount: Double,
    val type: String,
    val paymentMethod: String,
    val referenceId: Long
)