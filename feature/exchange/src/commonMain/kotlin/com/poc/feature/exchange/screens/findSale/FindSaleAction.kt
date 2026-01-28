package com.poc.feature.exchange.screens.findSale

sealed interface FindSaleAction {
    data object NavigateBack : FindSaleAction
    data class OnReceiptIdChanged(val newId: String) : FindSaleAction
    data object OnScanBarcodeClick : FindSaleAction
    data object OnSelectDateClick : FindSaleAction
    data class OnKeypadNumberClick(val number: String) : FindSaleAction
    data object OnKeypadClearClick : FindSaleAction
    data object OnKeypadBackspaceClick : FindSaleAction
    data object OnFindTransactionClick : FindSaleAction
}