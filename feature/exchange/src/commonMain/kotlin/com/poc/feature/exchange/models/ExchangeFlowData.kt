package com.poc.feature.exchange.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.poc.core.domain.models.PaymentMethodEnum
import com.poc.core.domain.models.Sale
import com.poc.core.domain.models.SaleItem
import com.poc.core.domain.models.SaleItemUI

data class ExchangeFlowState(
    val saleId: Long = 0L,
    val totalValueOfReturn: Double = 0.0,
    val totalValueOfSale: Double = 0.0,
    val totalValueOfSaleFormatted: String = "",
    val saleTimestamp: Long = 0L,
    val usedPaymentMethod: PaymentMethodEnum? = null,
    val totalValueOfReturnFormatted: String = "",
    val selectedProducts: List<ExchangeItemUI> = emptyList(),
)

class ExchangeFlowData {
    var exchangeFlowState by mutableStateOf(ExchangeFlowState())
}
