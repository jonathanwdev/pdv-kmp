package com.poc.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class ProductEntity(
    @PrimaryKey()
    val sku: Int,
    val name: String,
    val price: Double,
    val imageUrl: String
)
