package com.poc.feature.transaction.models

import com.poc.core.domain.models.PaymentMethodEnum



sealed interface TransactionItemUI {
    data class Sale(
        val id: Long,
        val transactionId: String,
        val referenceId: Long,
        val transactionValueFormatted: String,
        val transactionDate: Long,
        val transactionDateFormatted: String,
        val transactionTimeFormatted: String,
        val transactionMethod: PaymentMethodEnum,
        val transactionStatus: String
    ) : TransactionItemUI

    data class Exchange(
        val id: Long,
        val transactionId: String,
        val referenceId: Long,
        val transactionValueFormatted: String,
        val transactionDate: Long,
        val transactionDateFormatted: String,
        val transactionTimeFormatted: String,
        val transactionMethod: PaymentMethodEnum,
        val transactionStatus: String
    ) : TransactionItemUI
}