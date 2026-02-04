package com.poc.feature.exchange.screens.summary

sealed interface SummaryAction {
    data object OnBackClick : SummaryAction
    data object OnCompleteExchangeClick : SummaryAction
}