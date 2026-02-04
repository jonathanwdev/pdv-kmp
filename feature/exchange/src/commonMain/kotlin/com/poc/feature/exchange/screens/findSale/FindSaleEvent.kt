package com.poc.feature.exchange.screens.findSale

sealed interface FindSaleEvent {
    data object SaleFound : FindSaleEvent
    data object SaleNotFound : FindSaleEvent
}