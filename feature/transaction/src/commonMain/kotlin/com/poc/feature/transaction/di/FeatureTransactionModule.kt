package com.poc.feature.transaction.di

import com.poc.feature.transaction.screens.transactionDetails.TransactionDetailsViewModel
import com.poc.feature.transaction.screens.transactions.TransactionsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val featureTransactionsModule = module {
    viewModelOf(::TransactionsViewModel)
    viewModelOf(::TransactionDetailsViewModel)
}