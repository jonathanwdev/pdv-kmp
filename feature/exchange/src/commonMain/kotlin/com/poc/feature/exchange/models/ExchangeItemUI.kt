package com.poc.feature.exchange.models

data class ExchangeItemUI(
    val itemId: Long,
    val productSku: Int,
    val name: String,
    val maxQuantity: Int,
    val quantitySelected: Int,
    val imageUrl: String,
    val totalPrice: Double,
    val totalPriceFormatted: String,
    val unitPrice: Double,
    val unitPriceFormatted: String,
    val tax: Double,
    val taxFormatted: String,
)
