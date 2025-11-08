package com.poc.core.domain.models

data class Product(
    val id: String,
    val name: String,
    val price: Double,
    val description: String,
    val imageUrl: String
)
