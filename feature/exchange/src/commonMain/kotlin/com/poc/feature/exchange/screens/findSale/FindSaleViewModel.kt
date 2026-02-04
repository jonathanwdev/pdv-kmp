package com.poc.feature.exchange.screens.findSale

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poc.core.domain.repository.SaleRepository
import com.poc.feature.exchange.models.ExchangeFlowData
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FindSaleViewModel(
    private val saleRepository: SaleRepository,
    private val exchangeFlowData: ExchangeFlowData
) : ViewModel() {

    private val _event = Channel<FindSaleEvent>()
    val event = _event.receiveAsFlow()

    private val _state = MutableStateFlow(FindSaleState())
    val state = _state.asStateFlow()

    private fun findSaleBySaleId(saleId: String) {
        if (saleId.isBlank()) return
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            saleRepository
                .findSaleById(saleId = saleId.toLong())
                .fold(
                    onSuccess = { sale ->
                        if (sale != null) {
                            exchangeFlowData.exchangeFlowState = exchangeFlowData.exchangeFlowState.copy(
                                saleId = sale.saleId,
                            )
                            _event.send(FindSaleEvent.SaleFound)
                        } else {
                            _event.send(FindSaleEvent.SaleNotFound)
                        }
                    },
                    onFailure = {
                        _event.send(FindSaleEvent.SaleNotFound)
                    }
                )

        }
    }

    private fun onKeyBackspaceClick() {
        if(_state.value.receiptId.isBlank()) return
        _state.update { it.copy(receiptId = it.receiptId.dropLast(1)) }
    }

    private fun onKeyClearClick() {
        if(_state.value.receiptId.isBlank()) return
        _state.update { it.copy(receiptId = "") }
    }

    private fun onKeyNumberClickClick(keyNumber: String) {
        _state.update { it.copy(receiptId = it.receiptId + keyNumber) }
    }


    fun onAction(action: FindSaleAction) {
        when (action) {
            FindSaleAction.OnFindTransactionClick -> findSaleBySaleId(_state.value.receiptId)
            FindSaleAction.OnKeypadBackspaceClick -> onKeyBackspaceClick()
            FindSaleAction.OnKeypadClearClick -> onKeyClearClick()
            is FindSaleAction.OnKeypadNumberClick -> onKeyNumberClickClick(action.number)
            else -> Unit
        }
    }

}