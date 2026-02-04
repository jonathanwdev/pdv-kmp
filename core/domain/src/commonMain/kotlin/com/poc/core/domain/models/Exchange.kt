package com.poc.core.domain.models

data class Exchange(
    val exchangeId: Long,
    val originalSaleId: Long,
    val timestamp: Long,
    val reason: String?,
    val totalCreditValue: Double,
    val items: List<ExchangeItem>
)
