package com.poc.core.database.entities

import androidx.room.Embedded
import androidx.room.Relation

data class ExchangeItemWithDetails(
    @Embedded val exchangeItem: ExchangeItemEntity,

    @Relation(
        parentColumn = "saleItemId",
        entityColumn = "itemId"
    )
    val originalSaleItem: SaleItemEntity
)