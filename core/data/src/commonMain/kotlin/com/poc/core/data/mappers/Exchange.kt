package com.poc.core.data.mappers

import com.poc.core.database.entities.ExchangeItemWithDetails
import com.poc.core.database.entities.ExchangeWithCompleteDetails
import com.poc.core.domain.models.Exchange
import com.poc.core.domain.models.ExchangeItem

fun ExchangeWithCompleteDetails.toDomain(): Exchange {
    return Exchange(
        exchangeId = exchange.exchangeId,
        originalSaleId = exchange.originalSaleId,
        timestamp = exchange.timestamp,
        reason = exchange.reason,
        totalCreditValue = exchange.creditValue,
        items = items.map {
            it.toDomain()
        }
    )
}


fun ExchangeItemWithDetails.toDomain(): ExchangeItem {
    return ExchangeItem(
        exchangeItemId = exchangeItem.exchangeItemId,
        saleItemId = exchangeItem.saleItemId,
        productSku = originalSaleItem.productSku,
        productName = originalSaleItem.name,
        productImageUrl = originalSaleItem.imageUrl,
        quantityReturned = exchangeItem.quantity,
        creditValue = exchangeItem.creditValue,
        reason = exchangeItem.reason
    )
}

