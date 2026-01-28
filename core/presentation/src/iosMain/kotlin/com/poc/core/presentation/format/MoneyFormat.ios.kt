package com.poc.core.presentation.format
import platform.Foundation.NSNumber
import platform.Foundation.NSNumberFormatter
import platform.Foundation.NSNumberFormatterCurrencyStyle


actual fun Double.formatMoney(currencyFormat: FormatMoneyCurrency): String {
    val formatter = NSNumberFormatter().apply {
        numberStyle = NSNumberFormatterCurrencyStyle
        setCurrencyCode(currencyCode)
        setMinimumFractionDigits(2u)
        setMaximumFractionDigits(2u)
    }
    val number = NSNumber(double = this)
    return formatter.stringFromNumber(number) ?: "$${this}"
}