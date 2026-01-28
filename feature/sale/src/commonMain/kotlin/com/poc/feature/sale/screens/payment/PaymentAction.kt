package com.poc.feature.sale.screens.payment

import com.poc.core.domain.models.PaymentMethodEnum

sealed interface PaymentAction {
    data class OnPaymentMethodClick(val methodEnum: PaymentMethodEnum): PaymentAction
    data object OnChargeClick: PaymentAction
    data object OnGoBackClick: PaymentAction
    data object OnCancelClick: PaymentAction
}


