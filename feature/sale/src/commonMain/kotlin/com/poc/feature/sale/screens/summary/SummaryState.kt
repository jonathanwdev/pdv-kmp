package com.poc.feature.sale.screens.summary

import com.poc.core.presentation.format.DateFormat
import kotlin.time.Clock
import kotlin.time.Instant

data class SummaryState(
    val saleId: Long = 0L,
    val dateTime: Instant = Clock.System.now(),
    val totalValueFormatted: String = "",
    val userEmail: String = "",
    val isSent: Boolean = false,
    val isPrinted: Boolean = false,
) {
    val transactionId: String = "TXN${saleId}SL"
    val dateTimeFormatted: String = DateFormat.formatTransactionDateTime(this.dateTime)

}

