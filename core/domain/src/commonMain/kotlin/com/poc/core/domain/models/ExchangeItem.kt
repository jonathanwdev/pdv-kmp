package com.poc.core.domain.models

data class ExchangeItem(
    val exchangeItemId: Long,
    val saleItemId: Long,
    val productSku: Int,
    val productName: String,
    val productImageUrl: String,
    val quantityReturned: Int,
    val creditValue: Double,
    val reason: String?
)
