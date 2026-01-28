package com.poc.feature.sale.screens.bag

sealed interface BagAction {
    data object OnSearchProduct: BagAction
    data class OnChangeSkuInput(val sku: String): BagAction
    data object OnChargeClick: BagAction
    data object OnCancelClick: BagAction
    data class OnAddMoreItems(val sku: Int): BagAction
    data class OnRemoveItems(val sku: Int): BagAction
}