package com.poc.feature.transaction.screens.transactions

import com.poc.feature.transaction.models.TransactionItemUI
import com.poc.feature.transaction.models.TransactionsFilter

sealed interface TransactionsAction {
    data class OnFilterClick(val filter: TransactionsFilter): TransactionsAction
    data object OnBackClick: TransactionsAction
    data class OnTransactionClick(val transactionId: Long): TransactionsAction
}