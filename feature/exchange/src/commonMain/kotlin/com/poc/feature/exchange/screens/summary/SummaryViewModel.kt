package com.poc.feature.exchange.screens.summary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poc.core.domain.models.request.CreateExchangeRequest
import com.poc.core.domain.models.request.ExchangeItemRequest
import com.poc.core.domain.repository.ExchangeRepository
import com.poc.core.presentation.format.TransactionIdFormat
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

class SummaryViewModel(
    private val exchangeRepository: ExchangeRepository,
    private val exchangeFlowData: ExchangeFlowData
) : ViewModel() {

    private var hasLoadedInitialData = false
    private val _event = Channel<SummaryEvent>()
    val event = _event.receiveAsFlow()

    private val _state = MutableStateFlow(SummaryState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                populateState()
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = SummaryState()
        )

    private fun populateState() {
        val exchangeFlow = exchangeFlowData.exchangeFlowState
        println("****** exchangeFlow.selectedProducts"+ exchangeFlow.selectedProducts)
        _state.update {
            it.copy(
                transactionId = TransactionIdFormat.transactionIdToSale(exchangeFlow.saleId),
                subtotalReturned = exchangeFlow.totalValueOfReturn,
                totalValueOfReturnFormatted = exchangeFlow.totalValueOfReturnFormatted,
                totalValueOfSale = exchangeFlow.totalValueOfSale,
                totalValueOfSaleFormatted = exchangeFlow.totalValueOfSaleFormatted,
                transactionTimestamp = exchangeFlow.saleTimestamp,
                returnedItems = exchangeFlow.selectedProducts
            )
        }
    }

    private fun finishExchange() {
        viewModelScope.launch(Dispatchers.IO) {
            val saleId = exchangeFlowData.exchangeFlowState.saleId
            exchangeRepository.createExchange(
                CreateExchangeRequest(
                    originalSaleId = saleId,
                    reason = "return_${saleId}",
                    items = _state.value.returnedItems.map {
                        ExchangeItemRequest(
                            saleItemId = it.itemId,
                            quantity = it.quantitySelected,
                            creditValue = it.totalPrice,
                            reason = null
                        )
                    }
                ),
            ).onSuccess {
                _event.send(SummaryEvent.OnExchangeSuccess)
            }.onFailure {
                _event.send(SummaryEvent.OnExchangeError)
            }
        }
    }

    fun onAction(action: SummaryAction) {
        when (action) {
            SummaryAction.OnCompleteExchangeClick -> finishExchange()
            else -> Unit
        }
    }

}