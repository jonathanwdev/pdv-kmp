package com.poc.feature.exchange.di

import com.poc.feature.exchange.screens.findSale.FindSaleViewModel
import com.poc.feature.exchange.screens.selectItems.SelectItemsViewModel
import com.poc.feature.exchange.screens.summary.SummaryViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val featureExchangeModule = module {
    viewModelOf(::FindSaleViewModel)
    viewModelOf(::SelectItemsViewModel)
    viewModelOf(::SummaryViewModel)
}