package com.poc.feature.transaction.screens.transactionDetails

import com.poc.core.domain.models.TransactionTypeEnum
import com.poc.feature.transaction.components.SaleStatusChipEnum
import com.poc.feature.transaction.models.TransactionProductItemUI


data class TransactionDetailsState(
    val isLoading: Boolean = false,
    val transactionId: String = "",
    val status: SaleStatusChipEnum = SaleStatusChipEnum.COMPLETED,
    val dateFormatted: String = "",
    val timeFormatted: String = "",
    val cashierName: String = "John Due",
    val items: List<TransactionProductItemUI> = emptyList(),
    val subtotalFormatted: String = "",
    val taxFormatted: String = "",
    val totalFormatted: String = "",
    val transactionType: TransactionTypeEnum? = null
)

