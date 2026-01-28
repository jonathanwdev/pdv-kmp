package com.poc.feature.sale.screens.bag

import com.poc.core.presentation.format.formatMoney
import com.poc.feature.sale.models.SaleItemUI

data class BagState(
    val productSku: String = "",
    val saleId: Long = 0L,
    val taxPercentage: Int = 7,
    val totalAmount: Double = 0.0,
    val totalAmountFormatted: String = 0.0.formatMoney(),
    val subtotalAmount: Double = 0.0,
    val subtotalAmountFormatted: String = 0.0.formatMoney(),
    val totalTaxAmount: Double = 0.0,
    val totalTaxAmountFormatted: String = 0.0.formatMoney(),
    val items: List<SaleItemUI> = emptyList(),
)