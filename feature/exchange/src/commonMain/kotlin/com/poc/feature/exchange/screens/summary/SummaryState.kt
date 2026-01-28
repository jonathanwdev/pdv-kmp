package com.poc.feature.exchange.screens.summary

import com.poc.feature.exchange.screens.selectItems.ReturnItem
import com.poc.feature.exchange.screens.selectItems.returnItemsMock

data class SummaryState(
    val currentStep: Int = 3,
    val returnedItems: List<ReturnItem> = returnItemsMock,
    val subtotalReturned: Double = 70.00,
    val subtotalNew: Double = 125.00,
    val balanceDue: Double = 55.00,
    val transactionId: String = "TXN-82910-002",
    val transactionDateTime: String = "Oct 24, 2023 • 14:22 PM"
)


