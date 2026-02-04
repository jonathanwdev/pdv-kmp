package com.poc.core.database.entities

import androidx.room.Embedded
import androidx.room.Relation

data class ExchangeWithItems(
    @Embedded val exchange: ExchangeEntity,
    @Relation(
        parentColumn = "exchangeId",
        entityColumn = "exchangeId"
    )
    val items: List<ExchangeItemEntity>
)