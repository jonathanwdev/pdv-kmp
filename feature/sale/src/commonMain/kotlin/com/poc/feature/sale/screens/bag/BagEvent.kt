package com.poc.feature.sale.screens.bag

sealed interface BagEvent {
    data object OnFindProductError: BagEvent
}