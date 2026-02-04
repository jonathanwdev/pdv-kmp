package com.poc.feature.sale.screens.saleDetails

import com.poc.core.domain.models.SaleItemUI

data class SaleDetailsState(
    val taxPercentage: Int = 7,
    val subtotalAmountFormatted: String = "",
    val totalAmountFormatted: String = "",
    val totalTaxFormatted: String = "",
    val items: List<SaleItemUI> = emptyList(),
)