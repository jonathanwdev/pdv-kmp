package com.poc.feature.exchange.screens.selectItems

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poc.core.domain.repository.SaleRepository
import com.poc.core.presentation.format.TransactionIdFormat
import com.poc.core.presentation.format.formatMoney
import com.poc.feature.exchange.mappers.toExchangeItemUi
import com.poc.feature.exchange.models.ExchangeFlowData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SelectItemsViewModel(
    private val saleRepository: SaleRepository,
    private val exchangeFlowData: ExchangeFlowData
) : ViewModel() {

    private var hasLoadedInitialData = false
    private val _event = Channel<SelectItemsEvent>()
    val event = _event.receiveAsFlow()

    private val _state = MutableStateFlow(SelectItemsState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                getSale()
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = SelectItemsState()
        )

    private fun getSale() {
        viewModelScope.launch(Dispatchers.IO) {
            saleRepository
                .findSaleWithItemsById(exchangeFlowData.exchangeFlowState.saleId)
                .fold(
                    onSuccess = { sale ->
                        _state.update {
                            it.copy(
                                transactionId = TransactionIdFormat.transactionIdToSale(sale.saleId),
                                selectedSaleProducts = sale.items.map { it.toExchangeItemUi() }
                            )
                        }
                        exchangeFlowData.exchangeFlowState = exchangeFlowData.exchangeFlowState.copy(
                            usedPaymentMethod = sale.paymentMethod,
                            totalValueOfSale = sale.totalValue,
                            totalValueOfSaleFormatted = sale.totalValue.formatMoney(),
                            saleTimestamp = sale.timestamp
                        )
                    },
                    onFailure = {

                    }
                )
        }
    }

    private fun calculateTotals() {
        val totalPrice = _state.value.selectedSaleProducts.sumOf { item ->
            item.totalPrice * item.quantitySelected
        }
        _state.update {
            it.copy(
                totalPrice = totalPrice,
                enableSkip = totalPrice > 0,
                totalPriceFormatted = totalPrice.formatMoney()
            )
        }

    }

    private fun addMoreItems(sku: Int) {
        _state.update {
            it.copy(
                selectedSaleProducts = it.selectedSaleProducts.map { item ->
                    if (item.productSku != sku) return@map item
                    if (item.maxQuantity == item.quantitySelected) return@map item
                    item.copy(quantitySelected = item.quantitySelected + 1)
                }
            )
        }
        calculateTotals()
    }

    private fun removeItems(sku: Int) {
        _state.update {
            it.copy(
                selectedSaleProducts = it.selectedSaleProducts.map { item ->
                    if (item.productSku != sku) return@map item
                    if (item.quantitySelected == 0) return@map item
                    item.copy(quantitySelected = item.quantitySelected - 1)
                }
            )
        }
        calculateTotals()
    }

    private fun skipToNextStep() {
        exchangeFlowData.exchangeFlowState = exchangeFlowData.exchangeFlowState.copy(
            selectedProducts = _state.value.selectedSaleProducts.filter { it.quantitySelected > 0 },
            totalValueOfReturn = _state.value.totalPrice,
            totalValueOfReturnFormatted = _state.value.totalPriceFormatted
        )
        viewModelScope.launch {
            _event.send(SelectItemsEvent.OnSelectItemsSuccess)
        }
    }

    fun onAction(action: SelectItemsAction) {
        when (action) {
            is SelectItemsAction.OnAddItemClick -> addMoreItems(action.sku)
            is SelectItemsAction.OnRemoveItemClick -> removeItems(action.sku)
            SelectItemsAction.OnSelectReplacementItems -> skipToNextStep()
            else -> Unit
        }
    }

}