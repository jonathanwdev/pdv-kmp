package com.poc.core.domain.models.request

data class CreateExchangeRequest(
    val originalSaleId: Long,
    val reason: String?,
    val items: List<ExchangeItemRequest>
) {
    val totalCreditValue: Double
        get() = items.sumOf { it.creditValue * it.quantity }
}

data class ExchangeItemRequest(
    val saleItemId: Long,
    val quantity: Int,
    val creditValue: Double,
    val reason: String? = null
)
