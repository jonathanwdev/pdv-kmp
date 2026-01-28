package com.poc.feature.sale.screens.summary

sealed interface SummaryEvent {
    data object OnFinish: SummaryEvent
}