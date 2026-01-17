package com.poc.core.network.model

import kotlinx.serialization.Serializable


@Serializable
data class ProductResponse(
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val price: Double,
    val image: String,
    val rating: Rating? = null
)

@Serializable
data class Rating(
    val rate: Double,
    val count: Int
)
