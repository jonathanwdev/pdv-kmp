package com.poc.core.domain.models

data class SaleItem(
    val itemId: Long,
    val name: String,
    val saleId: Long,
    val productSku: Int,
    val quantity: Int,
    val returnedQuantity: Int,
    val imageUrl: String,
    val price: Double,
    val totalPrice: Double,
    val tax: Double,
)
