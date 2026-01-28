package com.poc.feature.transaction.screens.transactionDetails

sealed interface TransactionDetailsAction {
    data object NavigateBack : TransactionDetailsAction
    data object ReprintReceipt : TransactionDetailsAction
    data object IssueRefund : TransactionDetailsAction
}