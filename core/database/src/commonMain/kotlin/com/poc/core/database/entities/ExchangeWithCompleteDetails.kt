package com.poc.core.database.entities

import androidx.room.Embedded
import androidx.room.Relation

data class ExchangeWithCompleteDetails(
    @Embedded val exchange: ExchangeEntity,

    @Relation(
        entity = ExchangeItemEntity::class,
        parentColumn = "exchangeId",
        entityColumn = "exchangeId"
    )
    val items: List<ExchangeItemWithDetails>
)