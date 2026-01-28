package com.poc.feature.sale.models


data class SaleItemUI(
    val productSku: Int,
    val name: String,
    val quantity: Int,
    val imageUrl: String,
    val totalPrice: Double,
    val totalPriceFormatted: String,
    val unitPrice: Double,
    val unitPriceFormatted: String,
    val tax: Double,
    val taxFormatted: String,
)
