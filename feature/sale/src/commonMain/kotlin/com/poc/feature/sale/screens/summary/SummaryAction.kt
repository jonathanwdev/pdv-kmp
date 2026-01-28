package com.poc.feature.sale.screens.summary

sealed interface SummaryAction {
    data object OnReturnToHomeClick: SummaryAction

}