package com.poc.core.data.mappers

import com.poc.core.database.entities.SaleItemEntity
import com.poc.core.domain.models.SaleItem

fun SaleItemEntity.toDomain(): SaleItem {
    return SaleItem(
        itemId = itemId,
        quantity = quantity,
        price = price,
        productSku = productSku,
        imageUrl = imageUrl,
        saleId = saleId,
        isExchanged = isExchanged,
        name = name,
        totalPrice = totalPrice,
        tax = tax
    )
}


fun SaleItem.toEntity(): SaleItemEntity {
    return SaleItemEntity(
        quantity = quantity,
        price = price,
        productSku = productSku,
        imageUrl = imageUrl,
        saleId = saleId,
        isExchanged = isExchanged,
        name = name,
        totalPrice = totalPrice,
        tax = tax,
    )
}