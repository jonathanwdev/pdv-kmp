package com.poc.feature.exchange.screens.selectItems

sealed interface SelectItemsEvent {
    data object OnSelectItemsSuccess: SelectItemsEvent

}