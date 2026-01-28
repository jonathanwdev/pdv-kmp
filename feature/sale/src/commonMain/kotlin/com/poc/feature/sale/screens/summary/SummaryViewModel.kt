package com.poc.feature.sale.screens.summary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poc.core.domain.models.Sale
import com.poc.core.domain.models.SaleItem
import com.poc.core.domain.repository.SaleRepository
import com.poc.feature.sale.mappers.toSaleItem
import com.poc.feature.sale.models.SaleFlowData
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

class SummaryViewModel(
    private val saleRepository: SaleRepository,
    private val saleFlowData: SaleFlowData
) : ViewModel() {
    private val _event = Channel<SummaryEvent>()
    val event = _event.receiveAsFlow()


    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(SummaryState())

    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {

                _state.update { it.copy(
                    totalValueFormatted = saleFlowData.saleFlowState.totalAmountFormatted,
                    saleId = saleFlowData.saleFlowState.saleId,
                )}
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = SummaryState()
        )

    private suspend fun finishSale() {
        saleRepository.saveSale(
            sale = Sale(
                saleId = saleFlowData.saleFlowState.saleId,
                timestamp = _state.value.dateTime.toEpochMilliseconds(),
                taxPercentage = saleFlowData.saleFlowState.taxPercentage.toDouble(),
                totalValue = saleFlowData.saleFlowState.totalAmount,
                totalTaxValue = saleFlowData.saleFlowState.totalTaxAmount,
                paymentMethod = saleFlowData.saleFlowState.paymentMethodSelected!!,
                items = saleFlowData.saleFlowState.items.map { it.toSaleItem(saleFlowData.saleFlowState.saleId) }
            )
        )
        _event.send(SummaryEvent.OnFinish)

    }



    fun onAction(action: SummaryAction) {
       viewModelScope.launch {
           when (action) {
               SummaryAction.OnReturnToHomeClick -> {
                   finishSale()
               }
           }
       }
    }

}