package com.poc.feature.sale.screens.payment

import com.poc.core.domain.models.PaymentMethodEnum


data class PaymentState(
    val totalValueFormatted: String = "",
    val paymentMethodSelected: PaymentMethodEnum? = null,
)


