package com.poc.feature.transaction.screens.transactionDetails

sealed interface TransactionDetailsAction {
    data object OnNavigateBack : TransactionDetailsAction
    data object OnReprintReceipt : TransactionDetailsAction
    data object OnGoHomeClick : TransactionDetailsAction
}