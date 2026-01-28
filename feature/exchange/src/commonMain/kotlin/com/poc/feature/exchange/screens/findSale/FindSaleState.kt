package com.poc.feature.exchange.screens.findSale

data class FindSaleState(
    val receiptId: String = "REC-94021",
    val saleDate: Long? = null,
    val currentStep: Int = 1
)