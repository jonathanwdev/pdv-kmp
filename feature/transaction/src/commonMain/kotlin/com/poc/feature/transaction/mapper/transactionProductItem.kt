package com.poc.feature.transaction.mapper

import com.poc.core.domain.models.ExchangeItem
import com.poc.core.domain.models.SaleItem
import com.poc.core.presentation.format.formatMoney
import com.poc.feature.transaction.models.TransactionProductItemUI

fun SaleItem.toTransactionProductItemUI(): TransactionProductItemUI {
    return TransactionProductItemUI(
        sku = productSku,
        quantity = quantity,
        name = name,
        imageUrl = imageUrl,
        priceFormatted = totalPrice.formatMoney(),
        priceCalcFormatted = (totalPrice * quantity).formatMoney()
    )
}

fun ExchangeItem.toTransactionProductItemUI(): TransactionProductItemUI {
    return TransactionProductItemUI(
        sku = productSku,
        quantity = quantityReturned,
        name = productName,
        imageUrl = productImageUrl,
        priceFormatted = creditValue.formatMoney(),
        priceCalcFormatted = (creditValue * quantityReturned).formatMoney()
    )
}