package com.poc.core.domain.models

data class SaleUi(
    val saleId: Long,
    val timestamp: Long,
    val formattedDate: String,
    val totalValue: Double,
    val totalValueFormatted: String,
    val totalTaxValue: Double,
    val totalTaxValueFormatted: String,
    val taxPercentage: Double,
    val paymentMethod: PaymentMethodEnum,
    val items: List<SaleItemUI>
)
