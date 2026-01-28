package com.poc.core.data.mappers

import com.poc.core.database.entities.SaleEntity
import com.poc.core.database.entities.SaleWithProducts
import com.poc.core.domain.models.PaymentMethodEnum
import com.poc.core.domain.models.Sale
import com.poc.core.domain.models.SaleItem


fun Sale.toEntity(): SaleEntity  {
    return SaleEntity(
        saleId = saleId,
        timestamp = timestamp,
        totalValue = totalValue,
        totalTaxValue = totalTaxValue,
        taxPercentage = taxPercentage,
        paymentMethod = paymentMethod.name
    )
}

fun SaleEntity.toDomain(saleItems: List<SaleItem>): Sale {
    return Sale(
        saleId = saleId,
        timestamp = timestamp,
        totalValue = totalValue,
        totalTaxValue = totalTaxValue,
        taxPercentage = taxPercentage,
        paymentMethod = PaymentMethodEnum.valueOf(paymentMethod),
        items = saleItems
    )
}


fun SaleWithProducts.toDomain(): Sale {
    val items = this.saleItems.map { it.toDomain() }
    return this.sale.toDomain(items)
}