package com.poc.feature.sale.mappers

import com.poc.core.domain.models.Product
import com.poc.core.domain.models.SaleItem
import com.poc.core.presentation.format.formatMoney
import com.poc.feature.sale.models.SaleItemUI

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
        isExchanged = false,
        itemId = 0L
    )
}


fun Product.toSaleItemUi(): SaleItemUI {
    return SaleItemUI(
        productSku = sku,
        quantity = 1,
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