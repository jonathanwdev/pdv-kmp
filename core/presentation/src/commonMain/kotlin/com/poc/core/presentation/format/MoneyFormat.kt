package com.poc.core.presentation.format

enum class FormatMoneyCurrency {
    USD,
    EUR
}

expect fun Double.formatMoney(currencyFormat: FormatMoneyCurrency = FormatMoneyCurrency.USD): String