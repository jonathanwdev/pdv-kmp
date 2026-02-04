package com.poc.feature.exchange.mappers

import com.poc.core.domain.models.Sale
import com.poc.core.domain.models.SaleUi
import com.poc.core.presentation.format.DateFormat
import com.poc.core.presentation.format.formatMoney
import kotlin.time.Instant

fun Sale.toUI(): SaleUi {
    return SaleUi(
        saleId = saleId,
        timestamp = timestamp,
        totalValue = totalValue,
        totalTaxValue = totalTaxValue,
        formattedDate = DateFormat.formatTransactionDateWithDay(Instant.fromEpochMilliseconds(this.timestamp)),
        taxPercentage = taxPercentage,
        paymentMethod = paymentMethod,
        totalValueFormatted = totalValue.formatMoney(),
        totalTaxValueFormatted = totalTaxValue.formatMoney(),
        items = items.map { it.toUi() }
    )

}