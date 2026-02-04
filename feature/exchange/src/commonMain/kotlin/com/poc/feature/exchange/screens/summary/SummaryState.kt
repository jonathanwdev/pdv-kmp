package com.poc.feature.exchange.screens.summary

import com.poc.core.presentation.format.DateFormat
import com.poc.core.presentation.format.formatMoney
import com.poc.feature.exchange.models.ExchangeItemUI
import kotlin.time.Instant


data class SummaryState(
    val subtotalReturned: Double = 0.0,
    val totalValueOfSale: Double = 0.0,
    val totalValueOfSaleFormatted: String = "",
    val totalValueOfReturnFormatted: String = "",
    val transactionId: String = "",
    val transactionTimestamp: Long = 0L,
    val returnedItems: List<ExchangeItemUI> = emptyList()
) {
    val formattedDate = DateFormat.formatTransactionDateWithDay(Instant.fromEpochMilliseconds(this.transactionTimestamp))

}


