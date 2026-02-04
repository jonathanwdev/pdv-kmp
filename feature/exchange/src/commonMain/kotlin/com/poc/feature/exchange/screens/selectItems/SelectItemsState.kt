package com.poc.feature.exchange.screens.selectItems

import com.poc.core.presentation.format.formatMoney
import com.poc.feature.exchange.models.ExchangeItemUI


data class SelectItemsState(
    val transactionId: String = "",
    val totalPrice: Double = 0.0,
    val enableSkip: Boolean = false,
    val totalPriceFormatted: String = 0.0.formatMoney(),
    val selectedSaleProducts: List<ExchangeItemUI> = emptyList(),
)



