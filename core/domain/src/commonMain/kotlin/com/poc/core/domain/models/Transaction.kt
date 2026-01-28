package com.poc.core.domain.models

data class Transaction(
    val transactionId: Long,
    val timestamp: Long,
    val amount: Double,
    val type: TransactionTypeEnum,
    val paymentMethod: PaymentMethodEnum,
    val referenceId: Long
)