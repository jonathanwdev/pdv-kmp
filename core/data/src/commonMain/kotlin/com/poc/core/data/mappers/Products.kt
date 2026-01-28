package com.poc.core.data.mappers

import com.poc.core.database.entities.ProductEntity
import com.poc.core.domain.models.Product
import com.poc.core.network.model.ProductResponse

fun ProductResponse.toEntity(): ProductEntity {
    return ProductEntity(
        sku = id,
        name = title,
        price = price,
        imageUrl = image,
        tax = (price * 7) / 100
    )
}


fun ProductEntity.toDomain(): Product {
    return Product(
        sku = sku,
        name = name,
        price = price,
        totalPrice = price + tax,
        tax = tax,
        imageUrl = imageUrl
    )
}

fun ProductResponse.toDomain(): Product {
    return Product(
        sku = id,
        name = title,
        price = price,
        imageUrl = image,
        tax = (this.price * 7) / 100,
        totalPrice = ((this.price * 7) / 100) + price
    )
}



