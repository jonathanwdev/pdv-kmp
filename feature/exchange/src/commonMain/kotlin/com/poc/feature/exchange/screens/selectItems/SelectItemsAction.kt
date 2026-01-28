package com.poc.feature.exchange.screens.selectItems

sealed interface SelectItemsAction {
    data object NavigateBack : SelectItemsAction
    data object ToggleItemSelection: SelectItemsAction
    data object SelectReplacementItems : SelectItemsAction
}