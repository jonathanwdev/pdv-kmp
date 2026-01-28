package com.poc.feature.sale.screens.saleDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poc.feature.sale.models.SaleFlowData
import com.poc.feature.sale.models.SaleFlowState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class SaleDetailsViewModel(
    private val saleFlowData: SaleFlowData
) : ViewModel() {

    private var hasLoadedInitialData = false
    private val _state = MutableStateFlow(SaleDetailsState())
    val state = _state
        .map {
            mapSaleFlowDataToScreenState(saleFlowData.saleFlowState)
        }
        .onStart {
            if (!hasLoadedInitialData) {
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = SaleDetailsState()
        )

    private fun mapSaleFlowDataToScreenState(saleFlowState: SaleFlowState): SaleDetailsState {
        return SaleDetailsState(
            taxPercentage = saleFlowState.taxPercentage,
            subtotalAmountFormatted = saleFlowState.subtotalAmountFormatted,
            totalAmountFormatted = saleFlowState.totalAmountFormatted,
            totalTaxFormatted = saleFlowState.totalTaxAmountFormatted,
            items = saleFlowState.items
        )
    }

    fun onAction(action: SaleDetailsAction) {
        when (action) {

            else -> Unit
        }
    }

}