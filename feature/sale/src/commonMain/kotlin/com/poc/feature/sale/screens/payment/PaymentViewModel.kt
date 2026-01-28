package com.poc.feature.sale.screens.payment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poc.core.domain.models.PaymentMethodEnum
import com.poc.feature.sale.models.SaleFlowData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class PaymentViewModel(
    private val saleFlowData: SaleFlowData
) : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(PaymentState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                _state.update { it.copy(totalValueFormatted = saleFlowData.saleFlowState.totalAmountFormatted) }
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = PaymentState()
        )

    private fun onSelectPaymentMethodClick(methodEnum: PaymentMethodEnum?) {
        if(methodEnum == null) return
        _state.update { it.copy(paymentMethodSelected = methodEnum) }
    }

    fun onAction(action: PaymentAction) {
        when (action) {
            is PaymentAction.OnPaymentMethodClick -> {
                onSelectPaymentMethodClick(action.methodEnum)
            }
            PaymentAction.OnChargeClick -> {
                saleFlowData.saleFlowState = saleFlowData.saleFlowState.copy(
                    paymentMethodSelected = _state.value.paymentMethodSelected
                )
            }
            else -> Unit
        }
    }

}