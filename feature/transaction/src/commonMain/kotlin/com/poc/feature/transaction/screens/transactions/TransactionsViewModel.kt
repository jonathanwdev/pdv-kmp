package com.poc.feature.transaction.screens.transactions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poc.core.domain.models.TransactionTypeEnum
import com.poc.core.domain.repository.TransactionRepository
import com.poc.core.presentation.format.formatMoney
import com.poc.feature.transaction.mapper.toTransactionListUI
import com.poc.feature.transaction.models.TransactionsFilter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.math.ceil
import kotlin.time.Clock
import kotlin.time.Instant

class TransactionsViewModel(
    private val transactionRepository: TransactionRepository
) : ViewModel() {

    private val _filterSelected = MutableStateFlow(TransactionsFilter.All)
    private val _state = MutableStateFlow(TransactionsState())
    val state = combine(
        _state,
        _filterSelected,
        transactionRepository.getAllTransactions()
    ) { prevState, filter, transactions ->
        val filteredTransactionsDomain = when (filter) {
            TransactionsFilter.All -> transactions
            TransactionsFilter.Sales -> transactions.filter { it.type == TransactionTypeEnum.SALE }
            TransactionsFilter.Returns -> transactions.filter { it.type == TransactionTypeEnum.EXCHANGE }
        }

        val currentLocalDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date

        val dailyTransactions = transactions.filter { transaction ->
            val transactionLocalDate = Instant.fromEpochMilliseconds(transaction.timestamp)
                .toLocalDateTime(TimeZone.currentSystemDefault())
                .date
            transactionLocalDate == currentLocalDate
        }

        val saleTotal =
            dailyTransactions.filter { it.type == TransactionTypeEnum.SALE  }.sumOf { it.amount }
        val exchangeTotal =
            dailyTransactions.filter { it.type == TransactionTypeEnum.EXCHANGE }.sumOf { it.amount }
        val transactionTotal = saleTotal - exchangeTotal
        val percentageArchived = if (prevState.dailyGoal == 0.0) {
            0.0
        } else {
            transactionTotal / prevState.dailyGoal
        }

        prevState.copy(
            filteredTransactions = filteredTransactionsDomain.toTransactionListUI(),
            filterSelected = filter,
            totalValueFormatted = transactionTotal.formatMoney(),
            totalValue = transactionTotal,
            dailyPercentageAchieved = percentageArchived.toFloat()
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = TransactionsState()
    )

    private fun onFilterClick(filter: TransactionsFilter) {
        _filterSelected.update { filter }
    }

    fun onAction(action: TransactionsAction) {
        when (action) {
            is TransactionsAction.OnFilterClick -> onFilterClick(action.filter)
            is TransactionsAction.OnTransactionClick -> Unit
            else -> Unit
        }
    }

}