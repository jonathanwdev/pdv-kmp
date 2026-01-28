package com.poc.feature.exchange.screens.summary

sealed interface SummaryAction {
    data object NavigateBack : SummaryAction
    data object CompleteExchange : SummaryAction
}