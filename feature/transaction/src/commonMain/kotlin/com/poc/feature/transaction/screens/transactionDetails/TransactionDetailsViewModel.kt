package com.poc.feature.transaction.screens.transactionDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poc.core.domain.models.TransactionTypeEnum
import com.poc.core.domain.repository.ExchangeRepository
import com.poc.core.domain.repository.SaleRepository
import com.poc.core.domain.repository.TransactionRepository
import com.poc.core.presentation.format.DateFormat
import com.poc.core.presentation.format.TransactionIdFormat
import com.poc.core.presentation.format.formatMoney
import com.poc.feature.transaction.components.SaleStatusChipEnum
import com.poc.feature.transaction.mapper.toTransactionProductItemUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Instant

class TransactionDetailsViewModel(
    private val savedState: SavedStateHandle,
    private val transactionRepository: TransactionRepository,
    private val exchangeRepository: ExchangeRepository,
    private val saleRepository: SaleRepository
) : ViewModel() {
    val transactionId = savedState.get<Long>("transactionId") ?: throw NullPointerException()


    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(TransactionDetailsState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                getTransaction()
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = TransactionDetailsState()
        )

    private fun getTransaction() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            transactionRepository
                .getById(transactionId)
                .fold(
                    onSuccess = { transaction ->
                        val instantDate = Instant.fromEpochMilliseconds(transaction.timestamp)
                        val tax = transaction.amount * 0.07
                        val subtotal = transaction.amount - tax

                        when (transaction.type) {
                            TransactionTypeEnum.SALE -> {
                                saleRepository.findSaleWithItemsById(transaction.referenceId)
                                    .fold(
                                        onSuccess = { sale ->
                                            _state.update {
                                                it.copy(
                                                    isLoading = false,
                                                    transactionId = TransactionIdFormat.transactionIdToSale(
                                                        transaction.referenceId
                                                    ),
                                                    status = SaleStatusChipEnum.COMPLETED,
                                                    dateFormatted = DateFormat.formatTransactionDateWithDay(
                                                        instantDate
                                                    ),
                                                    timeFormatted = DateFormat.formatTransactionTime(
                                                        instantDate
                                                    ),
                                                    transactionType = transaction.type,
                                                    totalFormatted = transaction.amount.formatMoney(),
                                                    taxFormatted = tax.formatMoney(),
                                                    subtotalFormatted = subtotal.formatMoney(),
                                                    items = sale.items.map { prod -> prod.toTransactionProductItemUI() }
                                                )
                                            }
                                        },
                                        onFailure = {
                                            _state.update { it.copy(isLoading = false) }
                                        }
                                    )
                            }

                            else -> {
                                exchangeRepository.findExchangeById(transaction.referenceId)
                                    .fold(
                                        onSuccess = { exchange ->
                                            _state.update {
                                                it.copy(
                                                    isLoading = false,
                                                    transactionId = TransactionIdFormat.transactionIdToExchange(
                                                        transaction.referenceId
                                                    ),
                                                    status = SaleStatusChipEnum.REFUNDED,
                                                    dateFormatted = DateFormat.formatTransactionDateWithDay(
                                                        instantDate
                                                    ),
                                                    timeFormatted = DateFormat.formatTransactionTime(
                                                        instantDate
                                                    ),
                                                    transactionType = transaction.type,
                                                    totalFormatted = transaction.amount.formatMoney(),
                                                    taxFormatted = tax.formatMoney(),
                                                    subtotalFormatted = subtotal.formatMoney(),
                                                    items = exchange.items.map { prod -> prod.toTransactionProductItemUI() }
                                                )
                                            }
                                        },
                                        onFailure = {
                                            _state.update { it.copy(isLoading = false) }
                                        }
                                    )
                            }
                        }
                    },
                    onFailure = {
                        _state.update { it.copy(isLoading = false) }
                    }
                )
        }
    }

    fun onAction(action: TransactionDetailsAction) {
        when (action) {
            else -> Unit
        }
    }

}