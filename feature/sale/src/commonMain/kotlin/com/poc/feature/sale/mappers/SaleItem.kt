package com.poc.feature.sale.mappers

import com.poc.core.domain.models.Product
import com.poc.core.domain.models.SaleItem
import com.poc.core.presentation.format.formatMoney
import com.poc.core.domain.models.SaleItemUI


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