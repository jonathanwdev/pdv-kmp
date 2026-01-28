package com.poc.core.presentation.format

import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

actual fun Double.formatMoney(currencyFormat: FormatMoneyCurrency): String {
    val format = NumberFormat.getCurrencyInstance(Locale.getDefault()).apply {
        currency = Currency.getInstance(currencyFormat.name)
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }
    return format.format(this)
}