package com.poc.feature.sale.screens.summary

sealed interface SummaryAction {
    data object OnReturnToHomeClick: SummaryAction
    data class OnEmailChange(val email: String): SummaryAction
    data object OnSendClick: SummaryAction

}