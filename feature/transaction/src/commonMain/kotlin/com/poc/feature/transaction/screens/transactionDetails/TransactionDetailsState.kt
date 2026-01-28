package com.poc.feature.transaction.screens.transactionDetails

import com.poc.feature.transaction.components.sampleDetailItemMock

data class TransactionDetailsState(
    val transactionId: String = "#82941",
    val status: String = "Completed",
    val date: String = "Oct 24, 2023",
    val time: String = "2:45 PM",
    val cashierName: String = "Alex Johnson",
    val items: List<TransactionDetailItem> = sampleDetailItemMock,
    val subtotal: Double = 55.0,
    val tax: Double = 4.40,
    val discount: Double = 5.0,
    val discountLabel: String = "PROMO10",
    val total: Double = 54.40
)

data class TransactionDetailItem(
    val name: String,
    val quantity: Int,
    val price: Double,
    val imageUrl: String
)
