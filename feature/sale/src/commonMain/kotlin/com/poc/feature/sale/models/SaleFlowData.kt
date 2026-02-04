package com.poc.feature.sale.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.poc.core.domain.models.PaymentMethodEnum
import com.poc.core.domain.models.SaleItemUI
import com.poc.core.presentation.format.formatMoney

data class SaleFlowState(
    val saleId: Long = 0L,
    val taxPercentage: Int = 7,
    val totalAmount: Double = 0.0,
    val totalAmountFormatted: String = 0.0.formatMoney(),
    val subtotalAmount: Double = 0.0,
    val subtotalAmountFormatted: String = 0.0.formatMoney(),
    val totalTaxAmount: Double = 0.0,
    val totalTaxAmountFormatted: String = 0.0.formatMoney(),
    val paymentMethodSelected: PaymentMethodEnum? = null,
    val items: List<SaleItemUI> = emptyList(),
)

class SaleFlowData {
    var saleFlowState by mutableStateOf(SaleFlowState())
}
