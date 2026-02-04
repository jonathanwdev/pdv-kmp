package com.poc.feature.exchange.screens.selectItems

sealed interface SelectItemsAction {
    data object OnNavigateBack : SelectItemsAction
    data class OnAddItemClick(val sku: Int) : SelectItemsAction
    data class OnRemoveItemClick(val sku: Int) : SelectItemsAction
    data object OnSelectReplacementItems : SelectItemsAction
}