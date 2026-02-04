package com.poc.feature.sync.dummyData

import com.poc.core.domain.models.PaymentMethodEnum
import com.poc.core.domain.models.Sale
import com.poc.core.domain.models.SaleItem
import com.poc.core.presentation.format.DateFormat
import kotlin.random.Random

fun generateFakeSalesList(): List<Sale> {
    val sales = mutableListOf<Sale>()

    repeat(6) { idx ->
        val saleId = Random.nextLong(100_000, 1_000_000)
        sales.add(
            Sale(
                saleId = saleId,
                timestamp = DateFormat.dateLessOneDay(idx),
                totalValue = 1175.7052999999999,
                totalTaxValue = 76.9153,
                taxPercentage = 7.0,
                paymentMethod = PaymentMethodEnum.CASH,
                items = listOf(
                    SaleItem(
                        itemId = 0,
                        name = "John Hardy Women's Legends Naga Gold & Silver Dragon Station Chain Bracelet",
                        saleId = saleId,
                        productSku = 5,
                        quantity = 1,
                        returnedQuantity = 0,
                        imageUrl = "https://fakestoreapi.com/img/71pWzhdJNwL._AC_UL640_QL65_ML3_t.png",
                        price = 695.0,
                        totalPrice = 743.65,
                        tax = 48.65
                    ),
                    SaleItem(
                        itemId = 0,
                        name = "WD 4TB Gaming Drive Works with Playstation 4 Portable External Hard Drive",
                        saleId = saleId,
                        productSku = 12,
                        quantity = 2,
                        returnedQuantity = 0,
                        imageUrl = "https://fakestoreapi.com/img/61mtL65D4cL._AC_SX679_t.png",
                        price = 114.0,
                        totalPrice = 121.98,
                        tax = 7.98
                    ),
                    SaleItem(
                        itemId = 0,
                        name = "Mens Cotton Jacket",
                        saleId = saleId,
                        productSku = 3,
                        quantity = 1,
                        returnedQuantity = 0,
                        imageUrl = "https://fakestoreapi.com/img/71li-ujtlUL._AC_UX679_t.png",
                        price = 55.99,
                        totalPrice = 59.9093,
                        tax = 3.9193000000000002
                    ),
                    SaleItem(
                        itemId = 0,
                        name = "Lock and Love Women's Removable Hooded Faux Leather Moto Biker Jacket",
                        saleId = saleId,
                        productSku = 16,
                        quantity = 4,
                        returnedQuantity = 0,
                        imageUrl = "https://fakestoreapi.com/img/81XH0e8fefL._AC_UY879_t.png",
                        price = 29.95,
                        totalPrice = 32.0465,
                        tax = 2.0965000000000003
                    )
                )
            )
        )
    }
    return sales.toList()
}