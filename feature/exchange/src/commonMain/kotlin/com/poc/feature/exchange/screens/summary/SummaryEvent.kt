package com.poc.feature.exchange.screens.summary

sealed interface SummaryEvent {
    data object OnExchangeSuccess: SummaryEvent
    data object OnExchangeError: SummaryEvent
}