package com.poc.feature.transaction.mapper

import com.poc.core.domain.models.Transaction
import com.poc.core.domain.models.TransactionTypeEnum
import com.poc.core.presentation.format.DateFormat
import com.poc.core.presentation.format.formatMoney
import com.poc.feature.transaction.models.TransactionItemUI
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Instant

fun List<Transaction>.toTransactionListUI(): List<Map<String, List<TransactionItemUI>>> {
    val transactionsList = mutableListOf<Map<String, List<TransactionItemUI>>>()
    this
        .sortedByDescending { it.timestamp }
        .groupBy {
            Instant.fromEpochMilliseconds(it.timestamp)
                .toLocalDateTime(TimeZone.currentSystemDefault()).date
        }
        .forEach { (_, transactions) ->
            val transactionsUi = transactions.map { it.toTransactionUI() }
            val first = transactionsUi.first()
            val dateHeather = when (first) {
                is TransactionItemUI.Sale -> first.transactionDateFormatted
                is TransactionItemUI.Exchange -> first.transactionDateFormatted
            }
            transactionsList.add(mapOf(dateHeather to transactionsUi))
        }
    return transactionsList
}

fun Transaction.toTransactionUI(): TransactionItemUI {
    val dateToInstant = Instant.fromEpochMilliseconds(this.timestamp)
    return when (this.type) {
        TransactionTypeEnum.SALE -> TransactionItemUI.Sale(
            id = this.transactionId,
            transactionId = "TXN${this.referenceId}SL",
            referenceId = this.referenceId,
            transactionValueFormatted = this.amount.formatMoney(),
            transactionDate = this.timestamp,
            transactionDateFormatted = DateFormat.formatTransactionDateWithDay(dateToInstant),
            transactionTimeFormatted = DateFormat.formatTransactionTime(dateToInstant),
            transactionMethod = this.paymentMethod,
            transactionStatus = "COMPLETED"
        )

        TransactionTypeEnum.EXCHANGE -> TransactionItemUI.Exchange(
            id = this.transactionId,
            transactionId = "TXN${this.referenceId}EX",
            referenceId = this.referenceId,
            transactionValueFormatted = this.amount.formatMoney(),
            transactionDate = this.timestamp,
            transactionDateFormatted = DateFormat.formatTransactionDateWithDay(dateToInstant),
            transactionTimeFormatted = DateFormat.formatTransactionTime(dateToInstant),
            transactionMethod = this.paymentMethod,
            transactionStatus = "REFUNDED"
        )
    }
}