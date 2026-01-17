package com.poc.feature.sale.di

import com.poc.feature.sale.screens.bag.BagViewModel
import com.poc.feature.sale.screens.saleDetails.SaleDetailsViewModel
import com.poc.feature.sale.screens.summary.SummaryViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val featureSaleModule = module {
    viewModelOf(::BagViewModel)
    viewModelOf(::SaleDetailsViewModel)
    viewModelOf(::SummaryViewModel)
}