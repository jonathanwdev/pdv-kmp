package com.poc.feature.exchange.mappers

import com.poc.core.domain.models.SaleItem
import com.poc.core.domain.models.SaleItemUI
import com.poc.core.presentation.format.formatMoney
import com.poc.feature.exchange.models.ExchangeItemUI

fun SaleItem.toUi(): SaleItemUI {
    return SaleItemUI(
        productSku = productSku,
        quantity = quantity,
        imageUrl = imageUrl,
        totalPrice = totalPrice,
        totalPriceFormatted = totalPrice.formatMoney(),
        unitPrice = price,
        unitPriceFormatted = price.formatMoney(),
        name = name,
        tax = tax,
        taxFormatted = tax.formatMoney()
    )
}

fun SaleItem.toExchangeItemUi(): ExchangeItemUI {
    return ExchangeItemUI(
        productSku = productSku,
        maxQuantity = quantity - returnedQuantity,
        quantitySelected = 0,
        imageUrl = imageUrl,
        totalPrice = totalPrice,
        totalPriceFormatted = totalPrice.formatMoney(),
        unitPrice = price,
        unitPriceFormatted = price.formatMoney(),
        name = name,
        tax = tax,
        itemId = itemId,
        taxFormatted = tax.formatMoney()
    )
}

fun SaleItemUI.toSaleItem(
    saleId: Long
): SaleItem {
    return SaleItem(
        productSku = productSku,
        quantity = quantity,
        imageUrl = imageUrl,
        totalPrice = totalPrice,
        price = unitPrice,
        name = name,
        tax = tax,
        saleId = saleId,
        itemId = 0L,
        returnedQuantity = 0
    )
}
