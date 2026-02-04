package com.poc.feature.transaction.screens.transactions

import com.poc.core.domain.models.Transaction
import com.poc.core.presentation.format.formatMoney
import com.poc.feature.transaction.models.TransactionItemUI
import com.poc.feature.transaction.models.TransactionsFilter

data class TransactionsState(
    val filteredTransactions: List<Map<String, List<TransactionItemUI>>> = emptyList(),
    val transactions: List<Transaction> = emptyList(),
    val filterSelected: TransactionsFilter = TransactionsFilter.All,
    val totalValueFormatted: String = "",
    val totalValue: Double = 0.0,
    val dailyGoal: Double = 2000.00,
    val dailyPercentageAchieved: Float = 0.0f,
) {
    val dailyGoalFormatted = dailyGoal.formatMoney()
}