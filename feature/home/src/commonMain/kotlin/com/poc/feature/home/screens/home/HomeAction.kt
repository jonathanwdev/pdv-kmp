package com.poc.feature.home.screens.home

sealed interface HomeAction {
    data object OnNavigateToSaleClick : HomeAction
    data object OnNavigateToExchangeClick : HomeAction
    data object OnNavigateToTransactionsClick : HomeAction

}