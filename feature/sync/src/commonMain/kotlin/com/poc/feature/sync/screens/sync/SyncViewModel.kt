package com.poc.feature.sync.screens.sync

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poc.core.domain.models.PaymentMethodEnum
import com.poc.core.domain.models.Sale
import com.poc.core.domain.models.SaleItem
import com.poc.core.domain.repository.SaleRepository
import com.poc.core.domain.repository.SyncRepository
import com.poc.core.presentation.format.DateFormat
import com.poc.feature.sync.dummyData.generateFakeSalesList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pocpdv.feature.sync.generated.resources.Res
import pocpdv.feature.sync.generated.resources.sync_complete
import pocpdv.feature.sync.generated.resources.sync_configs
import pocpdv.feature.sync.generated.resources.sync_prices
import pocpdv.feature.sync.generated.resources.sync_products
import pocpdv.feature.sync.generated.resources.sync_sales
import kotlin.time.Clock

class SyncViewModel(
    private val syncRepository: SyncRepository,
    private val saleRepository: SaleRepository,
) : ViewModel() {

    private var hasLoadedInitialData = false
    private val _state = MutableStateFlow(SyncScreenState())

    private val _action = Channel<SyncScreenAction>()
    val action = _action.receiveAsFlow()


    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                startFakeSync()
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = SyncScreenState()
        )

    private fun insertFakeSales() {
        viewModelScope.launch(Dispatchers.IO) {
            val hasSales = saleRepository.findAllSalesWithItemsFlow().first()
            if(hasSales.isNotEmpty()) return@launch
            val fakeSales = generateFakeSalesList()
            fakeSales.forEach { sale ->
                saleRepository.saveSale(
                    sale = sale
                )
            }

        }
    }

    private fun startFakeSync() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(900)
            _state.update {
                it.copy(
                    progress = 0.2f,
                    message = Res.string.sync_products
                )
            }
            syncRepository.syncProducts()
            _state.update {
                it.copy(
                    progress = 0.4f,
                    message = Res.string.sync_configs
                )
            }
            delay(1500)
            _state.update {
                it.copy(
                    progress = 0.6f,
                    message = Res.string.sync_prices
                )
            }
            delay(1500)
            _state.update {
                it.copy(
                    progress = 0.8f,
                    message = Res.string.sync_sales
                )
            }
            insertFakeSales()
            _state.update {
                it.copy(
                    progress = 1f,
                    message = Res.string.sync_complete
                )
            }
            delay(1200)
            _state.update {
                it.copy(
                    isDone = true
                )
            }
            delay(800)
            _action.send(SyncScreenAction.OnSyncDone)
        }
    }
}