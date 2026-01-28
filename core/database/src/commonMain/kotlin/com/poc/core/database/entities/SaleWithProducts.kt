package com.poc.core.database.entities

import androidx.room.Embedded
import androidx.room.Relation

data class SaleWithProducts(
    @Embedded
    val sale: SaleEntity,
    @Relation(
        parentColumn = "saleId",
        entityColumn = "saleId"
    )
    val saleItems: List<SaleItemEntity>
)
