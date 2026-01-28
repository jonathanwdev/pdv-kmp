package com.poc.feature.sale.screens.saleDetails

sealed interface SaleDetailsAction {
    data object OnProceedClick: SaleDetailsAction
    data object OnEditClick: SaleDetailsAction
    data object OnCancelClick: SaleDetailsAction
    data object OnGoBackClick: SaleDetailsAction
}