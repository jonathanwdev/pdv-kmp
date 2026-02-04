package com.poc.core.presentation.format

object TransactionIdFormat {
    fun transactionIdToSale(transactionId: Long): String {
        return "TNX${transactionId}S"
    }
    fun transactionIdToExchange(transactionId: Long): String {
        return "TNX${transactionId}E"
    }
}