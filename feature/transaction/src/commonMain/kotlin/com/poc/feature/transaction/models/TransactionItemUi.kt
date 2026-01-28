package com.poc.feature.transaction.models

sealed interface TransactionItemUi {
    data class Sale(
        val transactionId: String,
        val transactionValue: String,
        val transactionDate: String,
        val transactionMethod: String,
        val transactionStatus: String
    ) : TransactionItemUi

    data class Exchange(
        val transactionId: String,
        val transactionValue: String,
        val transactionDate: String,
        val transactionMethod: String,
        val transactionStatus: String
    ) : TransactionItemUi
}