package com.poc.core.domain.models

data class Product(
    val sku: Int,
    val name: String,
    val price: Double,
    val tax: Double,
    val totalPrice: Double,
    val imageUrl: String
)

