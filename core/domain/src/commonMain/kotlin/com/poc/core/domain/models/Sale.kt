package com.poc.core.domain.models

data class Sale(
    val saleId: Long,
    val timestamp: Long,
    val totalValue: Double,
    val totalTaxValue: Double,
    val taxPercentage: Double,
    val paymentMethod: PaymentMethodEnum,
    val items: List<SaleItem>
)

