package com.poc.feature.exchange.di

import com.poc.feature.exchange.models.ExchangeFlowData
import com.poc.feature.exchange.models.ExchangeFlowScopeId
import com.poc.feature.exchange.screens.findSale.FindSaleViewModel
import com.poc.feature.exchange.screens.selectItems.SelectItemsViewModel
import com.poc.feature.exchange.screens.summary.SummaryViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val featureExchangeModule = module {
    scope(ExchangeFlowScopeId) {
        scoped { ExchangeFlowData() }
        viewModelOf(::FindSaleViewModel)
        viewModelOf(::SelectItemsViewModel)
        viewModelOf(::SummaryViewModel)
    }
}