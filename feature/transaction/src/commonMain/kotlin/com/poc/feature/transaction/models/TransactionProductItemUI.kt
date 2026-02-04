package com.poc.feature.transaction.models

data class TransactionProductItemUI(
    val sku: Int,
    val name: String,
    val priceFormatted: String,
    val priceCalcFormatted: String,
    val imageUrl: String,
    val quantity: Int
)
